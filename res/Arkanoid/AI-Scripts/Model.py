from keras.models import Sequential
from keras import optimizers
from keras.layers import Dense, Activation
import tensorflow as tf

config = tf.ConfigProto()
config.gpu_options.per_process_gpu_memory_fraction = 0.7
config.gpu_options.allow_growth = True
session = tf.Session(config=config)

class Model:

    def __init__(self, actionSize):
        self.actionSize = actionSize
        self.my_model = self.Model()
        self.my_model.load_weights('bestResult.h5')
        self.my_model.summary()

    def Model(self):
        model = Sequential()
        model.add(Dense(32, input_shape=(6,)))
        model.add(Activation("relu"))
        model.add(Dense(2))
        return model

    def train(self, x_train, y_train):
        adam = optimizers.adam(lr=5e-5)
        self.my_model.compile(loss='mean_squared_error', optimizer=adam)
        self.my_model.fit(x_train, y_train, batch_size=2, epochs=15, shuffle=True)
        self.my_model.save_weights('weights.h5')

    def predict(self, X):
        # print(X)
        y = self.my_model.predict(X)
        print(y)
        return y
