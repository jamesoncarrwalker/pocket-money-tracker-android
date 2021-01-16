package com.example.pocketmoneytracker.activities;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.pocketmoneytracker.R;
import com.example.pocketmoneytracker.abstractClasses.AbstractRequestActivity;
import com.example.pocketmoneytracker.datasource.TransactionApiObjectRequestObject;
import com.example.pocketmoneytracker.enums.DynamicEnvValue;
import com.example.pocketmoneytracker.enums.EnvVar;
import com.example.pocketmoneytracker.enums.TransactionType;
import com.example.pocketmoneytracker.helpers.DynamicEnvSelector;
import com.example.pocketmoneytracker.responseObjects.TransactionResponseObject;
import com.example.pocketmoneytracker.utils.Logging;

import java.util.HashMap;
import java.util.Map;


public class AddTransactionActivity extends AbstractRequestActivity {

    private TransactionApiObjectRequestObject transactionApiObject;
    private Map<String,Object> formData;
    private String formValidationErrorMessage, transactionDescription;
    private Float transactionAmount;
    private EditText amountInput, descriptionInput;
    private TextView submitTransactionErrorView;
    private RadioButton creditInputButton, spendInputButton;
    private RadioGroup transactionTypeRadioGroup;
    private ConstraintLayout transactionSubmitErrorWrapper;
    private Button submitTransactionButton;
    private TransactionType transactionType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        setupView();
        setDefaultFormValues();
    }

    public void setupView() {
        if(null == this.amountInput) {
            amountInput = findViewById(R.id.amount_input);
        }
        if(null == this.descriptionInput) {
            descriptionInput = findViewById(R.id.description_input);
        }
        if(null == this.creditInputButton) {
            creditInputButton = findViewById(R.id.credit_input_button);
        }
        if(null == this.spendInputButton) {
            spendInputButton = findViewById(R.id.spend_input_button);
        }
        if(null == this.transactionTypeRadioGroup){
            this.transactionTypeRadioGroup = findViewById(R.id.transaction_type_radio_group);
        }
        if(null == this.submitTransactionErrorView) {
            submitTransactionErrorView = findViewById(R.id.submit_transaction_error_view);
        }
        if(null == this.transactionSubmitErrorWrapper) {
            transactionSubmitErrorWrapper = findViewById(R.id.transaction_submit_error_wrapper);
        }

        if(null == this.submitTransactionButton) {
            this.submitTransactionButton = findViewById(R.id.submit_new_transaction_button);
            this.submitTransactionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    submitRequest();
                }
            });
        }

    }
    public void selectTransactionType(View view) {
        if(view.getId() == R.id.credit_input_button) {
            this.transactionType = TransactionType.CREDIT;
        } else {
            this.transactionType = TransactionType.SPEND;
        }
    }

    private void setDefaultFormValues() {
        setupView();
        amountInput.setText("0.00");
        descriptionInput.setText("");
        this.transactionTypeRadioGroup.check(this.creditInputButton.getId());
        transactionSubmitErrorWrapper.setVisibility(View.GONE);
    }

    @Override
    public void passResponse(Object response) {
        //if response has a transaction
        //kill the stack, launch the tracker
        //else display the error message
    }

    public void submitTransaction() {
        Logging.logStr("ABOUT TO VALIDATE","line 28");
        if(validateFormData()) {
            Logging.logStr("FORM VALIDATED","line 28");
            this.transactionApiObject.submitSingleTransaction(this.formData);
        } else {
            Logging.logStr("FAILED VALIDATION", formValidationErrorMessage);
        }

    }

    public void initApiRequestObject() {
        EnvVar apiBase = DynamicEnvSelector.EnvVar(DynamicEnvValue.API_BASE);
        assert apiBase != null;
        this.transactionApiObject = new TransactionApiObjectRequestObject(apiBase.getVar(), new TransactionResponseObject(), this, this);
    }

    @Override
    public void submitRequest() {
        if(null == this.transactionApiObject) {
            initApiRequestObject();
        }

        this.submitTransaction();
    }

    private boolean validateFormData() {
        this.formValidationErrorMessage = null;
        collectFormData();
        if(null == this.transactionAmount || null == this.transactionDescription) {
            this.formValidationErrorMessage = "Please fill in all fields";
        } else if (!(this.transactionAmount > 0) ) {
            this.formValidationErrorMessage = "Please add a number greater than 0";
        } else if (this.transactionDescription.length() < 3) {
            this.formValidationErrorMessage = "Please write enter a good description";
        }

        return this.formValidationErrorMessage == null;
    }

    private void collectFormData() {
        this.transactionAmount = Float.parseFloat(this.amountInput.getText().toString());
        this.transactionDescription = this.descriptionInput.getText().toString();
        this.setFormData();
    }

    private void setFormData() {
        this.formData = new HashMap<>();
        this.formData.put("amount",this.transactionAmount);
        this.formData.put("description", this.transactionDescription);
        this.formData.put("transactionType", this.transactionType);
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
