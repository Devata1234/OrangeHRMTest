package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Random;

public class PIMPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By pimMenu = By.xpath("//a[@href='/web/index.php/pim/viewPimModule']");
    private By addEmployeeBtn = By.xpath("//a[contains(@href,'addEmployee') or contains(text(),'Add Employee')]");
    private By firstNameField = By.name("firstName");
    private By lastNameField = By.name("lastName");
    private By employeeIdField = By.xpath("//label[text()='Employee Id']/../following-sibling::div/input");
    private By saveBtn = By.xpath("//button[@type='submit']");
    private By employeeListBtn = By.xpath("//a[contains(@href,'viewEmployeeList') or contains(text(),'Employee List')]");
    private By searchBox = By.xpath("//input[@placeholder='Type for hints...']");
    private By searchBtn = By.xpath("//button[@type='submit']");

    // Constructor
    public PIMPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Navigate to PIM module
    public void goToPIM() {
        wait.until(ExpectedConditions.elementToBeClickable(pimMenu)).click();
    }

    // Add a new employee with unique Employee ID
    public void addEmployee(String firstName, String lastName) {
        goToPIM();
        wait.until(ExpectedConditions.elementToBeClickable(addEmployeeBtn)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);

        // Generate random 6-digit ID to avoid duplicates and keep length ≤ 10
        String uniqueId = String.valueOf(100000 + new Random().nextInt(900000));
        WebElement idField = wait.until(ExpectedConditions.visibilityOfElementLocated(employeeIdField));
        idField.clear();
        idField.sendKeys(uniqueId);

        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
    }

    // Search for employee in Employee List
    public boolean searchEmployeeByName(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(employeeListBtn)).click();
        WebElement box = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        box.clear();
        box.sendKeys(name);
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();

        // Wait until search results load
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), name));
    }
}
