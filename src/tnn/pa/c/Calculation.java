/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tnn.pa.c;

import static java.lang.Math.acos;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.ArrayList;

/**
 *
 * @author Alex
 */
public class Calculation {
    
    /**
     *
     * @param array
     * @return
     */
    public double lengthVector(ArrayList<Double> array){
        double length=0.0;
        
        for (int i = 0; i < array.size(); i++) {
            length+=array.get(i)*array.get(i);
        }
        
        length=sqrt(length);
        
        return length;
    }
    
    
    public double distance(ArrayList<Double> array, ArrayList<Double> array2){
        double distance=0.0;
        
        for (int i = 0; i < array.size(); i++) {
            distance+= pow (  (array2.get(i)-array.get(i)) ,2);
        }
        distance= sqrt (distance);
        
        return distance;
    }
    
    
    public double angleVector(ArrayList<Double> array, ArrayList<Double> array2){
        double angle=0.0;
        double dot=0.0;
        //calculate the dot between the two vectors
        for (int i = 0; i < array.size(); i++) {
            dot+=array.get(i)*array2.get(i);
        }
        
        angle= ((double)dot/ (double)(this.lengthVector(array) * this.lengthVector(array2)));
        angle= acos(angle);
        
        
        return angle;
    }
    
    
}
