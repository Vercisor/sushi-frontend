package comp1206.sushi.server;
import comp1206.sushi.common.*;
import comp1206.sushi.mock.MockServer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Davide Gamba
 * @param <T> is a genereic object that is used to generate
 *           the table according to each Object's properties
 */
public class TableGenerator<T extends Model> {
    private List<T> data;
    private TableView tableview;
    private List<TableColumn> columns;
    private ObservableList<T> observableList;
    private ArrayList<Object> attributes;

    public TableGenerator(List<T> data, TableView tableview){
        this.tableview = tableview;
        this.data = data;
        this.observableList = FXCollections.observableArrayList(data);
        this.attributes = new ArrayList<>();
        this.attributes = getAttributes();
        this.columns = new ArrayList<>();
    }


    public void populateTable(){
        generateColumns(attributes);
        populateColumns(columns);
        addColumnsToTable(columns);
        tableview.setItems(observableList);
    }

    public ObservableList<T> getObservableList() {
        return observableList;
    }


    public void deleteRow(ObservableList<T> list, MockServer server){

        for (Object i : list) {

            if(i instanceof Postcode)
                if(((Postcode)i).getSelect().isSelected()) {
                    try {
                        server.removePostcode((Postcode)i);
                    } catch (ServerInterface.UnableToDeleteException e) {
                        e.printStackTrace();
                    }
                }

            if(i instanceof Staff)
                if(((Staff)i).getSelect().isSelected()) {
                    server.removeStaff((Staff)i);
                }

            if(i instanceof Drone)
                if(((Drone)i).getSelect().isSelected()) {
                    server.removeDrone((Drone)i);
                }

            if(i instanceof Supplier)
                if(((Supplier)i).getSelect().isSelected()) {
                    server.removeSupplier((Supplier)i);
                }

            if(i instanceof Ingredient)
                if(((Ingredient)i).getSelect().isSelected()) {
                    server.removeIngredient((Ingredient)i);
                }

            if(i instanceof Dish)
                if(((Dish)i).getSelect().isSelected()) {
                    server.removeDish((Dish)i);
                }
        }

    }

    private void addColumnsToTable(List<TableColumn> columns){

        tableview.getColumns().clear();

        for (TableColumn t: columns) {
            tableview.getColumns().add(t);
        }
    }

    private void populateColumns(List<TableColumn> columns){
        int x = 0;
        for (TableColumn t: columns){
            t.setCellValueFactory(new PropertyValueFactory<T, String>(attributes.get(x).toString()));
            x++;
        }

    }

    public List<TableColumn> getColumns() {
        return columns;
    }

    private void generateColumns(ArrayList<Object> listAttributes){
        for(Object o: listAttributes) {

            if(o instanceof CheckBox){
                TableColumn<T,CheckBox> column = new TableColumn(o.toString());
                columns.add(column);
            }else{
                @SuppressWarnings("unchecked")
                TableColumn<T,String> column = new TableColumn(o.toString());
                columns.add(column);
            }

        }
    }

    private ArrayList<Object> getAttributes(){
        try {
            Field[] attribute = data.get(0).getClass().getDeclaredFields();
            for (Field field: attribute)
            {
                int lastIndex = field.toString().lastIndexOf(".");
                attributes.add(field.toString().substring(lastIndex+1));
            }

        } catch(Exception e) {
            System.out.println(e.toString());
        }
        return attributes;
    }
}