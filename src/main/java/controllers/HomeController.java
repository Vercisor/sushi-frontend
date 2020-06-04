package controllers;

import comp1206.sushi.common.*;
import comp1206.sushi.mock.MockServer;
import comp1206.sushi.server.ServerInterface;
import comp1206.sushi.server.TableGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.*;


public class HomeController implements Initializable {


    private TableGenerator<Postcode> postTable;
    private TableGenerator<Staff> staff;
    private TableGenerator<Drone> drones;
    private TableGenerator<Supplier> suppliers;
    private TableGenerator<Ingredient> ingredients;
    private TableGenerator<Dish> dishes;
    private TableGenerator<Order> orders;
    private TableGenerator<User> users;


    MockServer server = new MockServer();
    URL location;
    ResourceBundle resources;


    @FXML
    private TableView PostcodeTable;

    @FXML
    private TableView StaffTable;

    @FXML
    private TableView DronesTable;

    @FXML
    private TableView SuppliersTable;

    @FXML
    private TableView IngredientsTable;

    @FXML
    private TableView DishesTable;

    @FXML
    private TableView OrdersTable;

    @FXML
    private TableView UsersTable;

    @FXML
    private TextField PostCodeText;

    @FXML
    private TextField StaffText;

    @FXML
    private TextField DroneText;

    @FXML
    private TextField SupplierText;

    @FXML
    private ChoiceBox ChoicePostcode;

    @FXML
    private TextField IngredientName;

    @FXML
    private TextField Unit;

    @FXML
    private TextField RestockAmount;

    @FXML
    private TextField RestockThreshold;

    @FXML
    private ChoiceBox ChoiceSupplier;

    @FXML
    private TextField DishName;

    @FXML
    private TextField DishPrice;

    @FXML
    private TextArea DishDescription;

    @FXML
    private TextField DishRestock;

    @FXML
    private TextField DishThreshold;

    @FXML
    private ChoiceBox ChoiceIngredient;

    @FXML
    private ChoiceBox ChoiceDish;

    @FXML
    private TextField IngredientQuantity;

    @FXML
    private TextField EditRestock;

    @FXML
    private TextField EditThreshold;

    @FXML
    private ChoiceBox selectIngredientEdit;

    private Alert postCodeError = new Alert(Alert.AlertType.ERROR);
    private Alert ingredientQuantityError = new Alert(Alert.AlertType.ERROR);
    private Alert supplierError = new Alert(Alert.AlertType.ERROR);
    private Alert blankError = new Alert(Alert.AlertType.ERROR);
    private Alert duplicateError = new Alert(Alert.AlertType.ERROR);
    private Alert ingredientError = new Alert(Alert.AlertType.ERROR);


    @FXML
    private void addPostcode(ActionEvent Event) throws Exception {

        if(PostCodeText.getText().isEmpty())
            blankError.showAndWait();
        else {

            for(Postcode p : postTable.getObservableList())
                if(p.getName().equals(PostCodeText.getText())) {
                    duplicateError.showAndWait();
                    throw new Exception("The postcode already exists");
                }

            server.addPostcode(PostCodeText.getText());
            PostCodeText.clear();
            refresh();
        }
    }

    @FXML
    private void addStaff(ActionEvent Event){

        if(StaffText.getText().isEmpty())
            blankError.showAndWait();
        else {
            server.addStaff(StaffText.getText());
            StaffText.clear();
            refresh();
        }
    }

    @FXML
    private void addDrone(ActionEvent Event){

        if(DroneText.getText().isEmpty())
            blankError.showAndWait();
        else {
            server.addDrone(Integer.parseInt(DroneText.getText()));
            DroneText.clear();
            refresh();
        }
    }

    @FXML
    private void addSupplier(ActionEvent Event){

        if(SupplierText.getText().isEmpty() || ChoicePostcode.getSelectionModel().isEmpty())
            blankError.showAndWait();
        else {
        server.addSupplier(SupplierText.getText(), (Postcode) ChoicePostcode.getValue());
        SupplierText.clear();
        refresh();
        }
    }

    @FXML
    private void addIngredient(ActionEvent Event){

        if(IngredientName.getText().isEmpty() ||
                Unit.getText().isEmpty()||
                ChoiceSupplier.getSelectionModel().isEmpty() ||
                RestockThreshold.getText().isEmpty() ||
                RestockAmount.getText().isEmpty())
            blankError.showAndWait();

        else {
            server.addIngredient(IngredientName.getText(), Unit.getText(),
                    (Supplier) ChoiceSupplier.getValue(),
                    Integer.parseInt(RestockThreshold.getText()),
                    Integer.parseInt(RestockAmount.getText()));
            IngredientName.clear();
            Unit.clear();
            RestockAmount.clear();
            RestockThreshold.clear();
            refresh();
        }
    }

    @FXML
    private void addDish(ActionEvent Event){
        if(DishName.getText().isEmpty() ||
                DishDescription.getText().isEmpty() ||
                DishPrice.getText().isEmpty() ||
                DishThreshold.getText().isEmpty() ||
                DishRestock.getText().isEmpty())
            blankError.showAndWait();

        else {
            server.addDish(DishName.getText(),
                    DishDescription.getText(),
                    Double.parseDouble(DishPrice.getText()),
                    Integer.parseInt(DishThreshold.getText()),
                    Integer.parseInt(DishRestock.getText()));
            refresh();
        }

    }

    @FXML
    private void addIngredientToDish(ActionEvent Event){

        if(IngredientQuantity.getText().isEmpty())
            blankError.showAndWait();
        else {
            server.addIngredientToDish((Dish) ChoiceDish.getValue(),
                    (Ingredient) ChoiceIngredient.getValue(),
                    Integer.parseInt(IngredientQuantity.getText()));
            IngredientQuantity.clear();
            refresh();
        }
    }




    @FXML
    private void removeIngredientFromDish(ActionEvent Event) throws ServerInterface.UnableToDeleteException {

        if(!(IngredientQuantity.getText().isEmpty())) {
            ingredientQuantityError.showAndWait();
            throw new ServerInterface.UnableToDeleteException("The quantity field must be empty for deletion");
        }
        server.removeIngredientFromDish((Dish) ChoiceDish.getValue(),
                                        (Ingredient) ChoiceIngredient.getValue());
        refresh();

    }

    @FXML
    private void removeDish(ActionEvent Event){
        dishes.deleteRow(dishes.getObservableList(), server);
        refresh();
    }

    @FXML
    private void removeIngredient(ActionEvent Event) throws ServerInterface.UnableToDeleteException {
        Map<Ingredient, Number> selects;
        for(Dish i : dishes.getObservableList()) {
            selects = i.getRecipe();
            for (Map.Entry<Ingredient, Number> entry : selects.entrySet())
                for(Ingredient in : ingredients.getObservableList())
                    if(entry.getKey().getName().equals(in.getName()) && in.getSelect().isSelected()) {
                        ingredientError.showAndWait();
                        throw new ServerInterface.UnableToDeleteException("You are trying to delete an ingredient that is already in use");
                    }
        }
        ingredients.deleteRow(ingredients.getObservableList(), server);
        refresh();
    }

    @FXML
    private void removePostcodes(ActionEvent Event) throws ServerInterface.UnableToDeleteException {

        for(Postcode p : postTable.getObservableList())
            for(Supplier s : suppliers.getObservableList())
                if(p.getSelect().isSelected() &&
                        s.getPostcode().getName().equals(p.getName())) {
                    postCodeError.showAndWait();
                    throw new ServerInterface.UnableToDeleteException(
                            "One or more postcodes that you are trying to delete " +
                                    "is being used by a supplier");
                }

        postTable.deleteRow(postTable.getObservableList(), server);
        refresh();
    }


    @FXML
    private void removeStaff(ActionEvent Event){
        staff.deleteRow(staff.getObservableList(), server);
        refresh();
    }

    @FXML
    private void removeDrones(ActionEvent Event){

        drones.deleteRow(drones.getObservableList(), server);
        refresh();
    }


    @FXML
    private void removeSuppliers(ActionEvent Event) throws ServerInterface.UnableToDeleteException {

        for(Supplier s : suppliers.getObservableList())
            for(Ingredient i : ingredients.getObservableList())
                if(s.getSelect().isSelected() &&
                        i.getSupplier().getName().equals(s.getName())) {
                    supplierError.showAndWait();
                    throw new ServerInterface.UnableToDeleteException(
                            "One or more Suppliers that you are trying to delete " +
                                    "is being used to provide an Ingredient");
                }
        suppliers.deleteRow(suppliers.getObservableList(), server);
        refresh();
    }

    /**
    @FXML
    private void editThreshold(ActionEvent Event) throws ServerInterface.UnableToDeleteException {

        Ingredient ingredient = (Ingredient) selectIngredientEdit.getValue();
        ingredient.setRestockThreshold(Integer.parseInt(EditThreshold.getText()));
    }
    */

    private void refresh(){
        initialize(location, resources);

    }
    public void initialize(URL location, ResourceBundle resources){

        this.location = location;
        this.resources = resources;

        supplierError.setTitle("ERROR");
        supplierError.setHeaderText("Supplier elimination invalid!");
        supplierError.setContentText("One or more Suppliers that you are trying to delete is being used to provide an Ingredient");

        postCodeError.setTitle("ERROR");
        postCodeError.setHeaderText("Postcode elimination invalid!");
        postCodeError.setContentText("One or more postcodes that you are trying to delete are being used by a supplier");

        ingredientError.setTitle("ERROR");
        ingredientError.setHeaderText("Ingredient elimination invalid!");
        ingredientError.setContentText("One or more ingredients that you are trying to delete are being used in a dish");

        ingredientQuantityError.setTitle("ERROR");
        ingredientQuantityError.setHeaderText("Ingredient elimination invalid!");
        ingredientQuantityError.setContentText("The quantity field must be empty for deletion");

        blankError.setTitle("ERROR");
        blankError.setHeaderText("Empty Fields Detected");
        blankError.setContentText("All the fields must be filled out in order to proceed");

        duplicateError.setTitle("ERROR");
        duplicateError.setHeaderText("Duplicate Object Detected");
        duplicateError.setContentText("You are trying to add an element that is already present in the list");

        postTable = new TableGenerator<>(server.getPostcodes(), PostcodeTable);
        postTable.populateTable();

        ChoicePostcode.setItems(postTable.getObservableList());

        staff = new TableGenerator<>(server.getStaff(), StaffTable);
        staff.populateTable();

        drones = new TableGenerator<>(server.getDrones(), DronesTable);
        drones.populateTable();

        suppliers = new TableGenerator<>(server.getSuppliers(), SuppliersTable);
        suppliers.populateTable();

        ChoiceSupplier.setItems(suppliers.getObservableList());

        ingredients = new TableGenerator<>(server.getIngredients(), IngredientsTable);
        ingredients.populateTable();

        ChoiceIngredient.setItems(ingredients.getObservableList());

        dishes = new TableGenerator<>(server.getDishes(), DishesTable);
        dishes.populateTable();

        ChoiceDish.setItems(dishes.getObservableList());

        orders = new TableGenerator<>(server.getOrders(), OrdersTable);
        orders.populateTable();

        users = new TableGenerator<>(server.getUsers(), UsersTable);
        users.populateTable();
    }


}
