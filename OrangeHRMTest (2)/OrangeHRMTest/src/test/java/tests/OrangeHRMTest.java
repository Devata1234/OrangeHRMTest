package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PIMPage;

public class OrangeHRMTest extends BaseTest {

    @Test(priority = 1)
    public void testAddAndVerifyEmployees() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        PIMPage pimPage = new PIMPage(driver);

        String[][] employees = {
                {"Sibasis", "Devata"},
                {"Ranjan", "Mishra"},
                {"Rohit", "Sharma"}
        };

        // Add employees
        for (String[] emp : employees) {
            pimPage.addEmployee(emp[0], emp[1]);
            Thread.sleep(5000); // wait to ensure employee is saved
        }

        // Verify employees
        for (String[] emp : employees) {
            Assert.assertTrue(pimPage.searchEmployeeByName(emp[0]),
                    "Employee not found: " + emp[0]);
            System.out.println(emp[0] + " Verified");
        }
    }
}
