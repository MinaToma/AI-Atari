import tensorflow as tf
config = tf.ConfigProto()
config.gpu_options.per_process_gpu_memory_fraction = 0.7
config.gpu_options.allow_growth = True
session = tf.Session(config=config)



from keras.models import Sequential
from keras import optimizers
from keras.layers import Dense, Activation

class model :
    def __init__(self):

        self.my_model = self.model()

        # self.my_model.load_weights('bestofbest.h5')
        # self.my_model.load_weights('weights.h5')
        self.my_model.load_weights('/mnt/844C248E4C247CD4/OOP_Project/src/AI-Scripts/weights.h5')
        # self.my_model.load_weights('nice.h5')
        self.my_model.summary();

    def model(self):
        model = Sequential()

        model.add(Dense(32, input_shape=(5,)))
        # model.add(Activation("relu"))
        model.add(Dense(16))
        # model.add(Activation("relu"))
        # model.add(Dense(4))
        # model.add(Activation("relu"))
        model.add(Dense(8))

        model.add(Dense(4))
        model.add(Dense(2))

        return model

    def train(self, X_train, y_train):

        self.adam = optimizers.adam(lr=2e-5)
        self.my_model.compile(loss='mean_squared_error', optimizer=self.adam)

        self.my_model.fit(X_train, y_train, batch_size=2, epochs=15, shuffle=True)
        self.my_model.save_weights('weights.h5')

    def predict(self, X):
        # print (X)
        y = self.my_model.predict(X)
        # print (y)
        return y
