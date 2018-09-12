// hive-udf example 
// remove string from stringlist included delimeter (",") 
// registration udf sequence 
// 1. compile to make class file 
// javac RemoveElement.java 
// 2. move to right location to match packagename 
// mv RemoveElement.class /rightlocation 
// 3. make jar file 
// jar cvf RemoveElement.jar /rightlocation/RemoveElement.class 
// 4. add jar file in hiva 
// add jar RemoveElement.jar 
// 5. create temporary funcation 
// create temporary function remove_element as 'packagename.RemoveElement'

package packagename;

import java.util.ArrayList; import java.util.List; import org.apache.hadoop.hive.ql.exec.UDF; import org.apache.hadoop.hive.ql.exec.UDFArgumentException; import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException; import org.apache.hadoop.hive.ql.metadata.HiveException;

public final class RemoveElement extends UDF {
  public String evaluate(String list, String removeString) throws HiveException { 
    String[] splits = list.split(","); 
    //System.out.println(list + " : " + splits.length + " " + removeString);

    ArrayList<String> result = new ArrayList<String>();

    boolean deleted = false;
    for(int i=0; i<splits.length; i++){
      System.out.println(splits[i] + " " + removeString);
      if(splits[i].equals(removeString) && !deleted){
        deleted = true;
      }
      else{
        result.add(splits[i]);
      }
    }

    String resultString = "";
    for(int i=0; i<result.size(); i++){
      if(i==0) { resultString = result.get(i); }
      else{
        resultString += "," + result.get(i);
      }
    }
    return resultString;
 }
}
