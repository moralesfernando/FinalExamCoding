package rocket.app.view;


import java.net.URL;
import java.util.ResourceBundle;

import eNums.eAction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	private TextField txtPayement1;
	
	@FXML
	private ComboBox<String> xTerm;

	@FXML
	private Label lblIncome;
	
	@FXML
	private Label lblExpenses;
	
	@FXML
	private Label lblCreditScore;

	@FXML
	private Label lblHouseCost;

	@FXML
	private Label lblxTerm;

	@FXML
	private Label lblMortgagePayment;
	
	@FXML
	private Label lblPayment;

	@FXML
	private Button btnPayment;
	
	@FXML
	private Label lblError;
	
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
		lq.setiTerm(Integer.parseInt(xTerm.getSelectionModel().getSelectedItem().toString()));
		
		a.setLoanRequest(lq);

		// send lq as a message to RocketHub
		mainApp.messageSend(lq);
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		double specificRate = lRequest.getdRate();
		double pmt = Math.abs(lRequest.getdPayment());
		double maximumpmt1 = .28 * lRequest.getIncome();
		double maxpmt2 = .36 * lRequest.getIncome()-lRequest.getExpenses();
		double maxPmt = Math.min(maximumpmt1, maxpmt2);
		String pmtString = String.format("%1$,.2f", Math.abs(lRequest.getdPayment()));
		if (pmt > maxPmt){
			lblPayment.setText("Error. Payment too high.");
		}
		else {
			
			lblPayment.setText("Pay"+pmtString+"at a rate of"+ lRequest.getdRate());
		}
	}
}