package rocket.app.view;

import eNums.eAction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import rocket.app.MainApp;
import rocketCode.Action;
import rocketData.LoanRequest;

import exceptions.RateException;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocketBase.RateBLL;

public class MortgageController
{
	private MainApp mainApp;
	
	@FXML
	private TextField txtIncome;

	@FXML
	private TextField txtExpenses;

	@FXML
	private TextField txtCreditScore;
	
	@FXML
	private TextField txtHouseCost;

	@FXML
	private ComboBox<String> cbTerm;

	@FXML
	private Label Income;
	
	@FXML
	private Label Expenses;
	
	@FXML
	private Label CreditScore;

	@FXML
	private Label HouseCost;
	
	@FXML
	private Button Payment;

	@FXML
	private Label Error;

	@FXML
	private Label MortgagePayment;

	@FXML
	private void initialize() 
	{
		cbTerm.getItems().add("15");
		cbTerm.getItems().add("30");
		cbTerm.getSelectionModel().select("15");
	}
	
	public void setMainApp(MainApp mainApp)
	{
		this.mainApp = mainApp;
	}
	
	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
		Object message = null;
		
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		
		lq.setIncome(Double.parseDouble(txtIncome.getText()));
		lq.setExpenses(Double.parseDouble(txtExpenses.getText()));
		lq.setiCreditScore(Integer.parseInt(txtCreditScore.getText()));
		lq.setiTerm(Integer.parseInt(cbTerm.getSelectionModel().getSelectedItem().toString()));
		
		a.setLoanRequest(lq);

		// send lq as a message to RocketHub
		mainApp.messageSend(lq);
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		double specificRate = lRequest.getdRate();
		double pmt = Math.abs(lRequest.getdPayment());
		String pmtString = String.format("%1$,.2f", Math.abs(lRequest.getdPayment()));
		MortgagePayment.setText(pmtString);
		
		String rateString = String.format("%1$,.2f", Math.abs(lRequest.getdRate()));
		MortgagePayment.setText(Double.toString(specificRate));
	}
}
