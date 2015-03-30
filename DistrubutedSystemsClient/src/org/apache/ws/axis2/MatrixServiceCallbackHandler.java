
/**
 * MatrixServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package org.apache.ws.axis2;

    /**
     *  MatrixServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class MatrixServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public MatrixServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public MatrixServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for sum method
            * override this method for handling normal response from sum operation
            */
           public void receiveResultsum(
                    org.apache.ws.axis2.MatrixServiceStub.SumResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from sum operation
           */
            public void receiveErrorsum(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for localMatrixMultiply method
            * override this method for handling normal response from localMatrixMultiply operation
            */
           public void receiveResultlocalMatrixMultiply(
                    org.apache.ws.axis2.MatrixServiceStub.LocalMatrixMultiplyResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from localMatrixMultiply operation
           */
            public void receiveErrorlocalMatrixMultiply(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for fake method
            * override this method for handling normal response from fake operation
            */
           public void receiveResultfake(
                    org.apache.ws.axis2.MatrixServiceStub.FakeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from fake operation
           */
            public void receiveErrorfake(java.lang.Exception e) {
            }
                


    }
    