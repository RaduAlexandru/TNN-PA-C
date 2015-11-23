/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tnn.pa.c;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Alex
 */
public class TNNPAC {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        
        Writer writerLength = new OutputStreamWriter(new FileOutputStream("./distribution Length" + ".txt"), UTF8);
        Writer writerAngle = new OutputStreamWriter(new FileOutputStream("./distribution angle" + ".txt"), UTF8);
        Writer writerDist = new OutputStreamWriter(new FileOutputStream("./distribution distance" + ".txt"), UTF8);
        
        writerLength.write("# X    Y");
        writerLength.write("\r\n");
        writerAngle.write("# X    Y");
        writerAngle.write("\r\n");
        writerDist.write("# X    Y");
        writerDist.write("\r\n");
        
        
        int dimensions=1;
        int num_vecs=10000;
        int seed = 100;
        
        for (int dim = 0; dim < 100; dim++) {
            
        
        
        ArrayList<ArrayList<Double>> array = new ArrayList<>();
        
        //Create the vectors
        Random generator = new Random(seed);
      
        for (int i = 0; i < num_vecs; i++) {
            array.add(new ArrayList<>());
            for (int j = 0; j < dimensions; j++) {
                array.get(i).add(  generator.nextDouble());
            }   
        }
        
        for (int i = 0; i < num_vecs; i++) {
            for (int j = 0; j < dimensions; j++) {
                //System.out.println("number" +array.get(i).get(j));
            }   
        }
        
        
        //Length
        Calculation calc=new Calculation();
        double average_length=0.0;
        
        for (int i = 0; i < num_vecs; i++) {
            average_length+=calc.lengthVector(array.get(i));
        }
        average_length=average_length/num_vecs;
        
        System.out.println("The expected value of the length is" + average_length);
        
        //Angle
        //First construct the diagonal vector which is actually just 1s
        ArrayList<Double> diagonal = new ArrayList<>();
        for (int i = 0; i < dimensions; i++) {
            diagonal.add(1.0);
        }
    
        double average_angle=0.0;
        for (int i = 0; i < num_vecs; i++) {
            average_angle+=Math.toDegrees(calc.angleVector(array.get(i),diagonal));
        }
        average_angle=average_angle/num_vecs;
        System.out.println("average angle is" + average_angle);
     
        
        
        //Distance
        //Grab a vector an calculate the average distance from t to every other one
        double average_distance=0.0;
        int distances_calculated=0;
        
        
        for (int i = 0; i < 2000; i++) {
            //Now that I have the vector iterate again and calculate the distance from it to every other one, excpet himself
            for (int j = 0; j < 2000; j++) {
                if (i!=j){
                    //Calculate distance from vector i to j
                    average_distance+=calc.distance(array.get(i),array.get(j));
                    distances_calculated++;
                }
            }
        }
        average_distance=average_distance/distances_calculated;
        System.out.println("The average distance is" + average_distance);
        
       
        
        writerLength.write(dimensions + " " + average_length);
        writerAngle.write(dimensions + " " + average_angle);
        writerDist.write(dimensions + " " + average_distance);
        writerLength.write("\r\n");
        writerAngle.write("\r\n");
        writerDist.write("\r\n");
        
        dimensions=dimensions+10;
        
        }
        
        writerLength.close();
        writerAngle.close();
        writerDist.close();
        
    }
    
}
