/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package feature_generation;

/**
 *
 * @author prash
 */
public class Functions {
    double[][] fft2(double[][] inputData)
    {
        int height = inputData.length;
        int width = inputData[0].length;
        double[][] realOut=new double[height][width];
        double[][] imagOut=new double[height][width];
        double[][] amplitudeOut=new double[height][width];
        // Two outer loops iterate on output data.
        for (int yWave = 0; yWave < height; yWave++)
        {
            for (int xWave = 0; xWave < width; xWave++)
            {
                // Two inner loops iterate on input data.
                for (int ySpace = 0; ySpace < height; ySpace++)
                {
                    for (int xSpace = 0; xSpace < width; xSpace++)
                    {
                        // Compute real, imag, and ampltude.
                        realOut[yWave][xWave] += (inputData[ySpace][xSpace] * Math
                                .cos(2
                                        * Math.PI
                                        * ((1.0 * xWave * xSpace / width) + (1.0
                                                * yWave * ySpace / height))))
                                / Math.sqrt(width * height);
                        imagOut[yWave][xWave] -= (inputData[ySpace][xSpace] * Math
                                .sin(2
                                        * Math.PI
                                        * ((1.0 * xWave * xSpace / width) + (1.0
                                                * yWave * ySpace / height))))
                                / Math.sqrt(width * height);
                        amplitudeOut[yWave][xWave] = Math
                                .sqrt(realOut[yWave][xWave]
                                        * realOut[yWave][xWave]
                                        + imagOut[yWave][xWave]
                                        * imagOut[yWave][xWave]);
                    }
                    //System.out.println(realOut[yWave][xWave] + " + "
                      //      + imagOut[yWave][xWave] + " i");
                }
            }
        }
        for (int yWave = 0; yWave < height; yWave++)
        { for (int xWave = 0; xWave < width; xWave++)
        {
            realOut[yWave][xWave]*=height;
            //System.out.print(realOut[yWave][xWave] + " + "+ imagOut[yWave][xWave] + " i \t");
        }
        //System.out.println();
        }
        return realOut;
    }
    /*
    1x5 to 5xparams[1] if flip is 1
    mat =[   0   1   2   3   4]
    repmat--> 0 0 0 0
              1 1 1 1
              2 2 2 2
              3 3 3 3
              4 4 4 4
    */
    public double[][] myretmap(double mat[],int params[],int flip)
    {
        if (flip==1)
            {
                //params[0]=1
                //params[1]=m2
                // 1x5 to 5xm2
                double newmat[][]=new double[params[0]*mat.length][params[1]];
                for( int i=0;i<mat.length;i++)
                {
                    for(int j=0;j<params[1];j++)
                        newmat[i][j]=mat[i];
                }
                System.out.println("When flip is 1");
                //display(newmat);
                return newmat;
            }
            if(flip==0)
            {
                //params[0]=m1
                //params[1]=1
                // 1x5 to m1x1
                double newmat[][]=new double[params[0]][params[1]*mat.length];
                for( int i=0;i<params[0];i++)
                {
                    for(int j=0;j<mat.length;j++)
                        newmat[i][j]=mat[j];
                }
                System.out.println("When flip is 0");
                //display(newmat);
                return newmat;
            }
            return null;
    }
    double[][] mat_pyth(double x[][],double y[][])
    {
        int cols=x.length;
        int rows=x[0].length;
        double rootmat[][]=new double[rows][cols];// Need to change if x and y are of different dimensions
        for (int i=0;i<rows;i++)
            for(int j=0;j<cols;j++)
                rootmat[i][j]=Math.sqrt((x[i][j]*x[i][j]+y[i][j]*y[i][j]));

        return rootmat;
    }
    static double [][]minarray(double[][] a,double[][] b)
    {
        int rows=a[0].length;
        int cols=a.length;
        double ans[][]=new double[rows][cols];
        for(int i=0;i<rows;i++)
            for(int j=0;j<cols;j++)
                ans[i][j]=(a[i][j]<b[i][j]?a[i][j]:b[i][j]);
        return ans;
    }
    public double factorial(double number)
    {
         int i,fact=1;

        for(i=1;i<=number;i++)
            fact=fact*i;
        return fact;
    }
    public double nCx( int n, int x)
    {
        return (factorial(n)/(factorial(n-x)*factorial(x)));
    }
    double[][] binopdf(int x, int n, double[][] p)
    {
        int rows=p[0].length;int cols=p.length;
        double bindist[][]=new double[rows][cols];
        for(int i=0;i<rows;i++)
            for(int j=0;j<cols;j++)
                bindist[i][j]=nCx(n,x)*Math.pow(p[i][j],x)*Math.pow((1-p[i][j]),(n-x));
        return bindist;
    }
}
