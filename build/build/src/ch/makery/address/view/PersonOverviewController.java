package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import org.controlsfx.dialog.Dialogs;

import ch.makery.address.MainApp;
import ch.makery.address.model.Person;
import ch.makery.address.util.DateUtil;

public class PersonOverviewController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    // Reference to the main application.
    private MainApp mainApp;
   

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PersonOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        // Borrar detalles persona 
        showPersonDetails (null);
        
     // Escuche los cambios de selección y mostrar los detalles persona cambiadas. 
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }
  

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
        
    }
    
    
     public void showPersonDetails(Person person) {
            if (person != null) {
                // Fill the labels with info from the person object.
                firstNameLabel.setText(person.getFirstName());
                lastNameLabel.setText(person.getLastName());
                streetLabel.setText(person.getStreet());
                postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
                cityLabel.setText(person.getCity());
                birthdayLabel.setText(DateUtil.format(person.getBirthday()));  
            } else {
                // Person is null, remove all the text.
                firstNameLabel.setText("");
                lastNameLabel.setText("");
                streetLabel.setText("");
                postalCodeLabel.setText("");
                cityLabel.setText("");
                birthdayLabel.setText("");
            }
             
        }
     
      public void handleDeletePerson() {
          int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
          /*en el caso de que el botn no fuera pulsado*/
          if (selectedIndex >= 0) {
              personTable.getItems().remove(selectedIndex);
          } else {
              // Nothing selected.
              Dialogs.create()
                  .title("No Selection")
                  .masthead("No has seleccionado ninguna persona")
                  .message("Por favor seleccione una persona de la tabla.")
                  .showWarning();
          }
      }
      
      /*
      * Se llama cuando el usuario hace clic en el nuevo botón. Abre un diálogo para editar
      * Detalles para una nueva persona.
      */
      
    
      	public void handleNewPerson() {
          Person tempPerson = new Person();
          boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
          if (okClicked) {
              mainApp.getPersonData().add(tempPerson);
          }
      }

      
      /*
      * Se llama cuando el usuario hace clic en el botón Editar. Abre un diálogo para editar
      * Detalles de la persona seleccionada.
      */
    
     public void handleEditPerson() {
          Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
          if (selectedPerson != null) {
              boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
              if (okClicked) {
                  showPersonDetails(selectedPerson);
              }

          } else {
              // Nada seleccionado.
              Dialogs.create()
                  .title("No Selection")
                  .masthead("No Person Selected")
                  .message("Please select a person in the table.")
                  .showWarning();
          }
      }
}
    
    
    
