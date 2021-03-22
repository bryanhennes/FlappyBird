/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator_assignment;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author bryanhennes
 */
public class LayoutGUIController implements Initializable {
    
    private String crrntArthOp = "DEFAULT";
    private String opp1 = "0";
    private boolean previousOp = false; //determine whether or not calculation was just performed
    private boolean isNeg = false; //determine whether or not number is negative
    private boolean decimal = false; //determine whether or not decimal was used, if so then we will need to handle double values in the end
    @FXML
    private TextField txtFld;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clearScreen(ActionEvent event) {
        
        txtFld.clear();
        decimal = false;
        isNeg = false;
    }
    
    @FXML
    private void negNum(ActionEvent event) {
        String previousNums;
        
        
        if(isNeg == false){ //if num was previously positive, now display previous num as a negative num
         
            previousNums =txtFld.getText();
            txtFld.clear();
            txtFld.setText(txtFld.getText()+"-" + previousNums);
            isNeg = true;
            
        }
        else{ //if num previously negative, now display previous num as a positive
           previousNums = txtFld.getText(); 
           txtFld.clear();
           txtFld.setText(txtFld.getText()+previousNums.substring(1));
           isNeg = false;
        }
                  
    }

    @FXML
    private void addNum(ActionEvent event) {
        Button btn = (Button)event.getSource();
        if(previousOp == true){ //if calculation was just performed, clear the screen as soon as new nums are entered
            
            txtFld.clear();
            previousOp = false;
        }
        
        previousOp = false;      
        
        
        switch(btn.getText()){
            
            case "1": 
                //first check if zero is first number inputted, if it is and this number is not a decimal then clear text field and get next num
                if(txtFld.getText() != null && !txtFld.getText().trim().isEmpty() && txtFld.getText().charAt(0) == '0' && decimal == false){
                    txtFld.clear();
                    txtFld.setText(txtFld.getText()+"1");
                    break;   
                }
                else {
                    txtFld.setText(txtFld.getText()+"1");
                    break;
                }
            case "2": 
                if(txtFld.getText() != null && !txtFld.getText().trim().isEmpty() && txtFld.getText().charAt(0) == '0' && decimal == false){
                    txtFld.clear();
                    txtFld.setText(txtFld.getText()+"2");
                    break;   
                }
                else {
                    txtFld.setText(txtFld.getText()+"2");
                    break;
                }  
            case "3": 
                if(txtFld.getText() != null && !txtFld.getText().trim().isEmpty() && txtFld.getText().charAt(0) == '0' && decimal == false){
                    txtFld.clear();
                    txtFld.setText(txtFld.getText()+"3");
                    break;   
                }
                else {
                    txtFld.setText(txtFld.getText()+"3");
                    break;
                }
            case "4": 
                if(txtFld.getText() != null && !txtFld.getText().trim().isEmpty() && txtFld.getText().charAt(0) == '0' && decimal == false){
                    txtFld.clear();
                    txtFld.setText(txtFld.getText()+"4");
                    break;   
                }
                else {
                    txtFld.setText(txtFld.getText()+"4");
                    break;
                }
            case "5": 
                if(txtFld.getText() != null && !txtFld.getText().trim().isEmpty() && txtFld.getText().charAt(0) == '0' && decimal == false){
                    txtFld.clear();
                    txtFld.setText(txtFld.getText()+"5");
                    break;   
                }
                else {
                    txtFld.setText(txtFld.getText()+"5");
                    break;
                }
            case "6": 
                if(txtFld.getText() != null && !txtFld.getText().trim().isEmpty() && txtFld.getText().charAt(0) == '0' && decimal == false){
                    txtFld.clear();
                    txtFld.setText(txtFld.getText()+"6");
                    break;   
                }
                else {
                    txtFld.setText(txtFld.getText()+"6");
                    break;
                }
            case "7": 
                if(txtFld.getText() != null && !txtFld.getText().trim().isEmpty() && txtFld.getText().charAt(0) == '0' && decimal == false){
                    txtFld.clear();
                    txtFld.setText(txtFld.getText()+"7");
                    break;   
                }
                else {
                    txtFld.setText(txtFld.getText()+"7");
                    break;
                }
            case "8": 
                if(txtFld.getText() != null && !txtFld.getText().trim().isEmpty() && txtFld.getText().charAt(0) == '0' && decimal == false){
                    txtFld.clear();
                    txtFld.setText(txtFld.getText()+"8");
                    break;   
                }
                else {
                    txtFld.setText(txtFld.getText()+"8");
                    break;
                }
            case "9": 
                if(txtFld.getText() != null && !txtFld.getText().trim().isEmpty() && txtFld.getText().charAt(0) == '0' && decimal == false){
                    txtFld.clear();
                    txtFld.setText(txtFld.getText()+"9");
                    break;   
                }
                else {
                    txtFld.setText(txtFld.getText()+"9");
                    break;
                }
            case "0": 
                if (txtFld.getText() == null || txtFld.getText().trim().isEmpty()) {
                    txtFld.setText(txtFld.getText()+"0");
                    break;
                }
                else {
                    if(txtFld.getText().charAt(0) == '0' && decimal == false){
                        break;
                    }
                    else{
                       txtFld.setText(txtFld.getText()+"0"); 
                    }
                       
                }
                    
                
                
                
               
        }
    }

    @FXML
    private void arithOp(ActionEvent event) {
        Button btnOp = (Button)event.getSource();
        switch(btnOp.getText()){
            case "+": 
                if(crrntArthOp.equals("DEFAULT")){
                    opp1 = txtFld.getText();
                    crrntArthOp = "ADD";
                    txtFld.clear();
                }break;
            case "-":
                if(crrntArthOp.equals("DEFAULT")){
                    opp1 = txtFld.getText();
                    crrntArthOp = "SUBTRACT";
                    txtFld.clear();
                }break;
            case "÷":
                if(crrntArthOp.equals("DEFAULT")){
                    opp1 = txtFld.getText();
                    crrntArthOp = "DIVIDE";
                    txtFld.clear();
                }break;
            case "x":
                if(crrntArthOp.equals("DEFAULT")){
                    opp1 = txtFld.getText();
                    crrntArthOp = "MULTIPLY";
                    txtFld.clear();
                }break;
        }
    }

    @FXML
    private void addDec(ActionEvent event) {
        Button decBut = (Button)event.getSource();
        if(decBut.getText().equals(".")){
            decimal = true;
            txtFld.setText(txtFld.getText()+".");
        }
        
    }

    @FXML
    private void processReturn(ActionEvent event) {
        if (decimal == false){
            int num1 = Integer.parseInt(opp1);
            int num2 = Integer.parseInt(txtFld.getText());
                if(crrntArthOp.equals("ADD")){
                    txtFld.setText("" + (num1+num2));
                }
                else if(crrntArthOp.equals("SUBTRACT")){
                    txtFld.setText("" + (num1-num2));
                }
                else if(crrntArthOp.equals("DIVIDE")){
                    try {//catch zero division exception and display "Undefined" if attempting zero division
                            txtFld.setText("" + (num1/num2));
                        }
                    catch (ArithmeticException e){
                            txtFld.setText("Undefined");
                    }
                }
                else if(crrntArthOp.equals("MULTIPLY")){
                    txtFld.setText("" + (num1*num2));
                }
        }
        else{
            double num1 = Double.parseDouble(opp1);
            double num2 = Double.parseDouble(txtFld.getText());
                if(crrntArthOp.equals("ADD")){
                    txtFld.setText("" + (num1+num2));
                }
                else if(crrntArthOp.equals("SUBTRACT")){
                    txtFld.setText("" + (num1-num2));
                }
                else if(crrntArthOp.equals("DIVIDE")){
                    try {//catch zero division exception and display "Undefined" if attempting zero division
                        txtFld.setText("" + (num1/num2));
                    }
                    catch (ArithmeticException e){
                        txtFld.setText("Undefined");
                    }
                }
                else if(crrntArthOp.equals("MULTIPLY")){
                    txtFld.setText("" + (num1*num2));
                }
            
        
        }
        
        
        crrntArthOp = "DEFAULT";
        previousOp = true;
        decimal = false;
        isNeg = false;
        
    }
    
}
