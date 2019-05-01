import numpy as np
from model import model


class connection:

    def __init__(self):

        self.ALLX = []
        self.ALLY = []
        self.model = model()


    def connect(self):

        k = 0
        while(True):

            file = open("/mnt/844C248E4C247CD4/OOP_Project/src/Resources/AI-Interaction/interaction.txt", "r")
            # file = open("/rc/AI-Intertest.txt", "r")

            mode = file.readline()

            if (mode[0:10] == 'predection') :

                y = self.predection(file)
                self.Write(y, mode)

            if(mode[0:8] == 'training'):

                # reading the number of fram we will train on
                numOfFrame = int(file.readline())

                # if the number of from is different then the number of from we predicted
                # that's mean there is an error
                if(numOfFrame != len(self.ALLX)) :
                     print("error, number of image is wrong ")
                     return

                else:
                    y = self.cslculat_reward(numOfFrame, file)
                    X= np.array(self.ALLX)
                    self.Write(0, mode)

                    try:
                        X = X.reshape(numOfFrame, -1)
                        self.model.train(X, y)

                        print ("doooone")
                        k+=1
                        print(k)

                    except:
                        print ("error...")

                    self.Write(0, "done")
                    self.ALLX *= 0
                    self.ALLY *= 0


            file.close()
            if(mode == "exit"):
                break


    def Write(self, y, mode):

        file = open("/mnt/844C248E4C247CD4/OOP_Project/src/Resources/AI-Interaction/interaction.txt", "w")

        file.truncate()

        if(mode[0:10] == "predection"):
            if(y ==0):
                file.write("right")
                print ("right")
            elif (y == 1) :
                file.write("left")
                print ("left")
            else:
                file.write("same")
                print ("same")

        if(mode == "training"):
            file.write("training...")

        if(mode =="done"):
            file.write("done")

        file.close()


    def predection(self,file):

        x = []
        for i in range(5):
            x.append(float(file.readline()))

        X = np.array(x)

        self.ALLX.append(X)

        # predicting the action
        X = X.reshape(1, -1)
        y = self.model.predict(X)

        # append the predection to Allpredection list to use it in the training time
        self.ALLY.append(y)



        return np.argmax(y)


    def cslculat_reward(self, numOfFrame, file):

        ground_truth = np.zeros((numOfFrame))

        y = np.zeros((numOfFrame, 2))
        # y = np.array(self.ALLY)

        for i in range(numOfFrame):
            temp = file.readline()
            print (temp)
            ground_truth[i] = float(temp)

        for i in range(numOfFrame-1, 1, -1):
            j = np.argmax(self.ALLY[i-1])
            k = np.argmax(self.ALLY[i])

            if(i==numOfFrame-1):
                y[i, k] = ground_truth[i]

            y[i-1, j] = (ground_truth[i-1] + ((y[i,k])*0.99))

        for i in range(numOfFrame):
            k = np.argmax(self.ALLY[i])
            y[i, k] /= 85
            # y[i,0, 1] /= 85

        y = y.reshape(numOfFrame, 2)
        print (y)
        return y
