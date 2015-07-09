 package org.apache.ws.axis2;
import java.rmi.RemoteException;
import org.apache.axis2.AxisFault;
import org.apache.ws.axis2.AddServiceStub.Add;



public class TestOperatorClassServer {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        
        AddServiceStub classStub = new AddServiceStub();
        
        Add add0 = new Add();
        
        add0.setA(20);
        add0.setB(30);
        
        int finalvalue = classStub.add(add0).get_return();
        System.out.println(finalvalue);
        

    }
}