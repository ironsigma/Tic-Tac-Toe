import java.util.*;
class TIC_TAC_TOE
{
    char ch[][]=new char[3][3];
    char a;
    void display()
    {

        System.out.println(" --- --- ---");

        for(int i = 0;i<3;i++)
        {
            for(int j = 0;j<3;j++)
            {
                System.out.print("| "+ch[i][j] +" ");

            }
            System.out.println("|");
        }
        System.out.println(" --- --- ---");
    }

    void initialize(int i,char sym )
    {
        switch(i)
        {
            case 1: ch[0][0]=sym;break;
            case 2: ch[0][1]=sym;break;
            case 3: ch[0][2]=sym;break;
            case 4: ch[1][0]=sym;break;
            case 5: ch[1][1]=sym;break;
            case 6: ch[1][2]=sym;break;
            case 7: ch[2][0]=sym;break;
            case 8: ch[2][1]=sym;break;
            case 9: ch[2][2]=sym;break;
        }
    }

    void input()
    {
        int c1=0,c2=0;
        int turn = 1;String name1="",name2="";
        Scanner sc = new Scanner(System.in);
        System.out.println("\nDo you want to enter your NAME player 1 ?");
        String chc1 = sc.next();
        chc1 = chc1.toLowerCase();
        if (chc1.equals("yes")||chc1.equals("yeah")||chc1.equals("ya")||chc1.equals("yup")||chc1.equals("yeai")||chc1.startsWith("y"))
        {
            System.out.println("Enter your name !");
            name1 = sc.next();
        }
        else
        {
            name1 = "unkn_pl1";
        }
        System.out.println("\nDo you want to enter your NAME  player 2?");
        String chc11 = sc.next();
        chc11 = chc11.toLowerCase();
        if (chc11.equals("yes")||chc11.equals("yeah")||chc11.equals("ya")||chc11.equals("yup")||chc11.equals("yeai")||chc11.startsWith("y"))
        {
            System.out.println("Enter your name !");
            name2 = sc.next();
        }
        else
        {
            name2 = "unkn_pl2";
        }
        while(true)
        {
            char i1 = '1';
            for(int i = 0;i<3;i++)
            {
                for(int j = 0;j<3;j++)
                {
                    ch[i][j] = (char)i1;
                    i1++;
                }
            }
            display();

            
                while(true)
                {
                    System.out.println("\n"+name1+":where do you want to enter?");
                    int x = sc.nextInt();
                    initialize(x,'0');
                    display();
                    if(check()==1)
                    {
                        System.out.println("\n"+name1+"(0) wins");c1++;break;
                    }
                    else if(check()==2)
                    {
                        System.out.println("\nno one wins");break;
                    }
                    System.out.println("\n"+name2+":where do you want to enter?");
                    int y = sc.nextInt();
                    initialize(y,'X');
                    display();
                    if(check()==1)
                    {

                        System.out.println("\n"+name2+"(X) wins");c2++;
                        break;

                    }
                    else if(check()==2)
                    {
                        System.out.println("\nno one wins");break;
                    }
                }
            
            System.out.println("\n\nWanna play again ? :D !!!!!!!!!!");
            String chc  = sc.next();
            chc = chc.toLowerCase();
            if(chc.equals("yes")||chc.equals("yeah")||chc.equals("ha")||chc.equals("yup")||chc.equals("yeai")||chc.equals("y")||chc.startsWith("y")||chc.startsWith("h"))

            {
                turn++; String temp  = name2;
                name2 = name1;
                name1 = temp;
                continue;
            }
            else
            {
                break;
            }
        }
        System.out.println("\n\nSCORES:-");
        if(c1>c2)
        {
            System.out.println("\n\n"+name1+"\t"+name2);
            System.out.println(c1+"\t"+c2);System.out.println("\n"+name1+" WINS");
            display2();
        }
        else
        {
            System.out.println("\n\n"+name2+"\t"+name1);
            System.out.println(c2+"\t"+c1);System.out.println("\n"+name2+" WINS");
            display2();
    }
}

    int check()
    {
        int check=0;int m = 0;

        for(int i = 0;i<3;i++)
        {
            for(int j = 0;j<3;j++)
            {
                if(ch[i][j]>48&&ch[i][j]<=57)
                {
                    m++;break;
                }

            }
        }

        if((ch[0][0]==ch[0][1]&&ch[0][1]==ch[0][2])||(ch[1][0]==ch[1][1]&&ch[1][1]==ch[1][2])||(ch[2][0]==ch[2][1]&&ch[2][1]==ch[2][2])||(ch[0][0]==ch[1][0]&&ch[1][0]==ch[2][0])||(ch[0][1]==ch[1][1]&&ch[1][1]==ch[2][1])||(ch[2][0]==ch[2][1]&&ch[2][1]==ch[2][2])||(ch[0][0]==ch[1][1]&&ch[1][1]==ch[2][2])||(ch[0][2]==ch[1][1]&&ch[1][1]==ch[2][0]))
        {

            check = 1;return check;
        }
        if(m==0)
        {
            check = 2;return check;
        }
        return 0;
    }

    void display2()

    {
        System.out.println("\nIf you enjoyed playing please rate us out of five ->");
        Scanner sc = new Scanner(System.in);
        double rate = sc.nextDouble();
        System.out.println("\n         Thanks Again ");

        System.out.println("\n\n"+(char)(169)+" Cubix Pro Enterprises Private Limited");

    }

    public static void main(String args[])
    {
        TIC_TAC_TOE t1 = new TIC_TAC_TOE();
        t1.input();
    }
}
