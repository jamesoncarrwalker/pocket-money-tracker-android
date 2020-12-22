package com.example.pocketmoneytracker.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.pocketmoneytracker.R;
import com.example.pocketmoneytracker.datasource.TransactionApiObjectRequestObject;
import com.example.pocketmoneytracker.enums.DynamicEnvValue;
import com.example.pocketmoneytracker.enums.EnvVar;
import com.example.pocketmoneytracker.helpers.DynamicEnvSelector;
import com.example.pocketmoneytracker.interfaces.ResponseHandlerInterface;
import com.example.pocketmoneytracker.responseObjects.TransactionResponseObject;

import java.util.Map;


public class AddTransactionActivity extends AppCompatActivity implements ResponseHandlerInterface {

    private TransactionApiObjectRequestObject transactionApiObject;
    private Map<String,Object> formData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);


    }

    @Override
    public void passResponse(Object response) {
        //if response has a transaction
        //kill the stack, launch the tracker
        //else display the error message
    }

    private void submitTransaction() {
        if(null == this.transactionApiObject) {
            initTransactionApiObject();
        }
        if(validateFormData()) {
            this.transactionApiObject.submitSingleTransaction(this.formData);
        }

    }

    private void initTransactionApiObject() {
        EnvVar apiBase = DynamicEnvSelector.EnvVar(DynamicEnvValue.API_BASE);
        assert apiBase != null;
        this.transactionApiObject = new TransactionApiObjectRequestObject(apiBase.getVar(), new TransactionResponseObject(), this, this);
    }

    private boolean validateFormData() {
        boolean dataIsValid = true;
        //do some validation checks
        //else
        // highlight some missing data
        //and....
        return dataIsValid;
    }

    private void collectFormData() {
        //get the values
        //from the fields
        //and save in
        //this.formData
    }

    private void updateErrorView(TextView errorView, String errorMessage) {

    }

    private boolean validateDescription() {


        return false;
    }

    private boolean validateAmount() {

        return false;
    }
}
