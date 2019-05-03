import random

import numpy as np
from keras import Sequential
from keras.layers import Dense, Activation
from keras.optimizers import SGD

interactionsPath = "interaction.txt"
SCREENWIDTH = 1280.0
SCREENHEIGHT = 720.0
PIPEGAPSIZE = 450
BASEY = SCREENHEIGHT
load_saved_pool = 1
save_current_pool = 1
current_pool = []
fitness = []
maxXDist = 0
total_models = 50
generation = 1

# initialise all models
for i in range(total_models):
    model = Sequential()
    model.add(Dense(output_dim=7, input_dim=3))
    model.add(Activation("sigmoid"))
    model.add(Dense(output_dim=1))
    model.add(Activation("sigmoid"))

    sgd = SGD(lr=0.01, decay=1e-6, momentum=0.9, nesterov=True)
    model.compile(loss="mse", optimizer=sgd, metrics=["accuracy"])
    current_pool.append(model)
    fitness.append(0)

if load_saved_pool:
    for i in range(total_models):
        current_pool[i].load_weights("Current_Model_Pool/model_new" + str(i) + ".keras")

for i in range(total_models):
    print(current_pool[i].get_weights())


def save_pool():
    for xi in range(total_models):
        current_pool[xi].save_weights("Current_Model_Pool/model_new" + str(xi) + ".keras")
    print("Saved current pool!")


def model_crossover(model_idx1, model_idx2):
    global current_pool
    weights1 = current_pool[model_idx1].get_weights()
    weights2 = current_pool[model_idx2].get_weights()
    weightsnew1 = weights1
    weightsnew2 = weights2
    weightsnew1[0] = weights2[0]
    weightsnew2[0] = weights1[0]
    return np.asarray([weightsnew1, weightsnew2])


def model_mutate(weights):
    for xi in range(len(weights)):
        for yi in range(len(weights[xi])):
            if random.uniform(0, 1) > 0.85:
                change = random.uniform(-0.5, 0.5)
                weights[xi][yi] += change
    return weights


def predict_action(height, dist, pipe_height, model_num):
    global current_pool
    global maxXDist
    global fitness
    maxXDist = max(maxXDist, dist)

    print(height, dist, pipe_height, model_num, fitness[model_num])

    # The height, dist and pipe_height must be between 0 to 1 (Scaled by SCREENHEIGHT)
    height = min(SCREENHEIGHT, height) / SCREENHEIGHT
    dist = dist / maxXDist  # Max pipe distance from player will be 450
    pipe_height = min(SCREENHEIGHT, pipe_height) / SCREENHEIGHT
    neural_input = np.asarray([height, dist, pipe_height])
    neural_input = np.atleast_2d(neural_input)
    output_prob = current_pool[model_num].predict(neural_input, 1)[0]

    print(height, dist, pipe_height, model_num)

    if output_prob[0] <= 0.5:
        # Perform the jump action
        return 1
    return 2


def train():
    print("training")
    """Perform genetic updates here"""
    global current_pool
    global fitness
    global generation
    new_weights = []
    total_fitness = 0
    for select in range(total_models):
        total_fitness += fitness[select]
    for select in range(total_models):
        fitness[select] /= total_fitness
        if select > 0:
            fitness[select] += fitness[select - 1]
    for select in range(int(total_models / 2)):
        parent1 = random.uniform(0, 1)
        parent2 = random.uniform(0, 1)
        idx1 = -1
        idx2 = -1
        for idxx in range(total_models):
            if fitness[idxx] >= parent1:
                idx1 = idxx
                break
        for idxx in range(total_models):
            if fitness[idxx] >= parent2:
                idx2 = idxx
                break
        new_weights1 = model_crossover(idx1, idx2)
        updated_weights1 = model_mutate(new_weights1[0])
        updated_weights2 = model_mutate(new_weights1[1])
        new_weights.append(updated_weights1)
        new_weights.append(updated_weights2)
    for select in range(len(new_weights)):
        fitness[select] = 0
        current_pool[select].set_weights(new_weights[select])
    if save_current_pool == 1:
        save_pool()
    generation = generation + 1


def getReadyForPrediction(file):
    print("get Ready to predict")
    height = float(file.readline())
    dist = float(file.readline())
    center = float(file.readline())
    idx = int(file.readline())
    passed = int(file.readline())

    fitness[idx] += 1
    print(passed, idx,
          "SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS")
    if passed == 1:
        fitness[idx] += 25

    action = predict_action(height, dist, center, idx)

    if action == 1:
        write("jump", "prediction")
    else:
        write("stay", "prediction")


def connect():
    while True:
        with open(interactionsPath) as file:
            mode = file.readline()

            if mode[0:10] == 'prediction':
                print("in Prediction")
                getReadyForPrediction(file)

            if mode[0:8] == 'training':
                train()
                write("", "done")


def write(action, mode):
    file = open(interactionsPath, "w")
    file.truncate()

    if mode[0:10] == "prediction":
        file.write(action)

    if mode == "done":
        file.write("done")

    file.close()


if __name__ == '__main__':
    connect()
