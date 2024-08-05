
package com.nikiarsov.helpers;
/*** Present print context class
 * @author Nikolai Arsov
 * @version 1.0
 */


public class PrintContext {
   private Printable printable;

   public PrintContext(Printable printable){
      this.printable = printable;
   }

   
   /** 
    * @param color
    * @param arg
    */
   public void executePrint(String color, String arg){
      printable.execPrint(color, arg);
   }
}