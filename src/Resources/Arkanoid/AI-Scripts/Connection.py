import numpy as np
from Model import Model
from random import randint
import random


class Connection:

    def __init__(self):
        self.actionsPath = "action.txt"
        self.inputSizePath = "actionSize.txt"
        self.interactionsPath = "interactions.txt"
        self.actionSize = 2
        self.allX = []
        self.allY = []
        self.actions = list()
        self.actions.append("right")
        self.actions.append("left")
        self.model = Model(self.actionSize)

    def connect(self):
        while True:
            with open(self.interactionsPath) as file:
                mode = file.readline()
                if mode[0:10] == 'prediction':
                    y = self.prediction(file)
                    print(y)
                    self.write(y, mode)

                if mode[0:8] == 'training':

                    # reading the number of frame we will train on
                    numOfFrame = int(file.readline())

                    if numOfFrame != len(self.allX):
                        print(numOfFrame)
                        print(self.allX)
                        print(len(self.allX))
                        print("error, number of image is wrong ")
                        return
                    else:

                        y = self.calculate_reward(numOfFrame, file)
                        X = np.array(self.allX)
                        self.write(0, mode)

                        try:
                            X = X.reshape(numOfFrame, -1)
                            self.model.train(X, y)
                            print ("doooone")

                        except:
                            print ("error...")

                        self.write(0, "done")
                        self.allX *= 0
                        self.allY *= 0

                if mode == "exit":
                    break

    def write(self, y, mode):

        file = open(self.interactionsPath, "w")
        file.truncate()
        if mode[0:10] == "prediction":
            file.write(self.actions[y])
            print("prediction  " + self.actions[y])

        if mode == "training":
            file.write("training...")

        if mode == "done":
            file.write("done")

        file.close()

    def prediction(self, file):

        x = []
        for i in range(6):
            x.append(float(file.readline()))

        X = np.array(x)

        self.allX.append(X)

        # Predicting the action
        X = X.reshape(1, -1)
        y = self.model.predict(X)
        # append the prediction to All prediction list to use it during training time
        self.allY.append(y)

        return np.argmax(y)

    def calculate_reward(self, numOfFrame, file):

        ground_truth = np.zeros(numOfFrame)
        print("action size is ", self.actionSize)
        y = np.zeros((numOfFrame, self.actionSize))

        for i in range(numOfFrame):
            numberOfFrames=file.readline()
            # print(numberOfFrames)
            try:
                ground_truth[i] = float(numberOfFrames)
            except:
                print("error")

        for i in range(numOfFrame-1, 1, -1):
            j = np.argmax(self.allY[i-1])
            k = np.argmax(self.allY[i])

            if i == numOfFrame-1:
                y[i, k] = ground_truth[i]

            y[i-1, j] = (ground_truth[i-1] + ((y[i, k])*0.90))

        y = y.reshape((numOfFrame, self.actionSize))
        print(y)
        return y
