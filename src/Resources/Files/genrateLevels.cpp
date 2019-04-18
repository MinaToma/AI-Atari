#include <bits/stdc++.h>

using namespace std;
int main() {

    freopen("levels.txt","w",stdout);
    int levels =100;
    int numOfBricks=18;
    int sizeNormal = 130;
    int sizeSqaure = 44;
    int width = 1000;
    for(int level=1 ;level<=levels ; level++)
    {
        /*
         * random rkm hy3ml 2h
1:400 - normal bricks with same color without space
401:460 - normal bricks with same color with space
461:800 - normal bricks with 2 color without space
801:820 - normal bricks with 2 color with space
821:900 - normal bricks with sqaure without space
901:920 - normal bricks with square with space
921:1000 - square without space
1001:1020 - square with space
1021:1100 - square 2 colors without space
1101:1120 - square 2 colors with space

         *
         */
        if(numOfBricks%2)
            numOfBricks++;
        cout<<"Level"<<level<<endl<<numOfBricks<<endl;
        int temp = numOfBricks;
        int rnd ;
        int rnd2 ;
        int rnd3 ;
        int rnd4 ;

        if(level<=10)
        {
            //1,7
            rnd=1;
            rnd2=7;
            rnd3=101;
            rnd4=107;
        }
        else if(level<=20)
        {
            // 8,3
            rnd=8;
            rnd2=3;
            rnd3=108;
            rnd4=103;
        }
        else if(level<=30)
        {
            //1,9
            rnd=1;
            rnd2=9;
            rnd3=101;
            rnd4=109;
        }
        else if(level<=40)
        {
            //9,13
            rnd=9;
            rnd2=13;
            rnd3=109;
            rnd4=113;
        }
        else if(level<=50)
        {
            //10,7
            rnd=10;
            rnd2=7;
            rnd3=110;
            rnd4=107;
        }
        else if(level<=60)
        {
            // 5,2
            rnd=2;
            rnd2=5;
            rnd3=102;
            rnd4=105;
        }
        else if(level<=70)
        {
            //2,6
            rnd=2;
            rnd2=6;
            rnd3=102;
            rnd4=106;
        }
        else if(level<=80)
        {
            //14, 8
            rnd=14;
            rnd2=8;
            rnd3=114;
            rnd4=108;
        }
        else if(level<=90)
        {
            //4,9
            rnd=4;
            rnd2=9;
            rnd3=104;
            rnd4=109;
        }
        else
        {
            //5,14
            rnd=5;
            rnd2=14;
            rnd3=105;
            rnd4=114;
        }


        while(temp>0)
        {
            int widthTemp=width;
            int random = rand()%1120 +1;
            if(random <= 400)
            {

                while(sizeNormal*2<widthTemp && temp)
                {
                    if(random%2==0)
                    cout<<rnd<<" ";
                    else
                    cout<<rnd2<<" ";
                    widthTemp-=sizeNormal*2;
                    temp-=2;
                }
                cout<<endl;
            }
            else if(random <= 460)
            {

                while(sizeNormal*2<widthTemp && temp)
                {
                    int r = rand()%4;
                    if(r) {
                        if(random%2 == 0)
                            cout << rnd << " ", temp -= 2, widthTemp -= sizeNormal * 2;
                        else
                            cout << rnd2 << " ", temp -= 2, widthTemp -= sizeNormal * 2;
                    }
                    else
                        cout<<"0 ",widthTemp-=sizeSqaure*2;


                }
                cout<<endl;
            }
            else if(random <= 800)
            {

                while(sizeNormal*2<widthTemp && temp)
                {
                    int r = rand()%2;
                    if(r)
                        cout<<rnd<<" ";
                    else
                        cout<<rnd2<<" ";
                    widthTemp-=sizeNormal*2;
                    temp-=2;
                }
                cout<<endl;
            }
            else if(random <=820)
            {


                while(sizeNormal*2<widthTemp && temp)
                {
                    int r = rand()%4;

                    if(r) {
                        if(r%2)
                        cout << rnd << " ";
                        else
                        cout << rnd2<< " ";
                        widthTemp-=sizeNormal*2;
                        temp-=2;
                    } else
                        cout<<"0 ",widthTemp-=sizeSqaure;


                }
                cout<<endl;

            }
            else if(random <= 900)
            {



                while(sizeNormal*2<widthTemp && temp)
                {
                    int r = rand()%2;

                        if(r) {
                            if(random%2 == 0) {
                                cout << rnd << " ", widthTemp -= sizeNormal * 2;
                            } else
                            {
                                cout << rnd2 << " ", widthTemp -= sizeNormal * 2;

                            }
                        }
                        else
                            cout << rnd3 << " " , widthTemp-=sizeSqaure*2;

                        temp-=2;

                }
                cout<<endl;

            }
            else if(random <= 920)
            {

                while(sizeNormal*2<widthTemp && temp)
                {
                    int r = rand()%4;
                    if(r) {
                        if (r % 2){
                            if(random%2 == 0)
                            cout << rnd  << " ", widthTemp -= sizeNormal*2;
                            else
                                cout << rnd2  << " ", widthTemp -= sizeNormal*2;

                        }
                        else
                            cout << rnd3 << " ", widthTemp -= sizeSqaure*2;

                        temp-=2;
                    } else
                        cout<<"0 ", widthTemp-=sizeSqaure*2;

                }
                cout<<endl;
            }
            else if(random <= 1000)
            {
               

                while(sizeSqaure*2<widthTemp && temp)
                {
                    if(random%2 == 0)
                    cout << rnd3 << " ", widthTemp -= sizeSqaure*2;
                    else
                        cout << rnd4 << " ", widthTemp -= sizeSqaure*2;

                    temp-=2;
                }
                cout<<endl;
            }
            else if(random <= 1020)
            {
                

                while(sizeSqaure < widthTemp/2 && temp)
                {
                    int r= rand()%4;
                    if(r) {
                        if(random%2 == 0 )
                        cout << rnd3 << " ", temp -= 2;
                        else
                            cout << rnd4 << " ", temp -= 2;

                    }
                    else
                        cout<<"0 ";

                    widthTemp -= sizeSqaure*2;

                }
                cout<<endl;
            }
            else if(random <= 1100)
            {
                


                while(sizeSqaure*2<widthTemp && temp)
                {
                    int r= rand()%2;
                    if(r)
                        cout << rnd3 << " ";
                    else
                        cout<<rnd4<<" ";
                    widthTemp -= sizeSqaure*2;
                    temp-=2;
                }
                cout<<endl;
            }
            else if(random <= 1120)
            {
               

                while(sizeSqaure*2<widthTemp && temp)
                {
                    int r= rand()%4;
                    if(r) {
                        if (r % 2)
                            cout << rnd3 << " ";
                        else
                            cout << rnd4 << " ";

                        temp-=2;
                    } else
                        cout<<"0 ";
                    widthTemp -= sizeSqaure *2;

                }
                cout<<endl;

            }


        }
        cout<<"end"<<endl;
        numOfBricks+= rand()%4+4;






    }

    return 0;
}
