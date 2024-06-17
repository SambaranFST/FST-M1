import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.select import Select


# Create a fixture to initialize our webdriver
@pytest.fixture(scope="session")
def setup(request):
    # Initialize the webdriver
    driver = webdriver.Firefox()

    # Get the underlying collection
    session = request.node

    # Pass through the driver to the test class
    for item in session.items:
        cls = item.getparent(pytest.Class)
        setattr(cls.obj, "driver", driver)

    # Execute the test method
    yield

    # Close the browser once the test has ended
    request.addfinalizer(driver.close)


@pytest.mark.usefixtures("setup")
class TestSite:

    def test_postjob(self):
        self.driver.get("https://alchemy.hguy.co/jobs")

        Jobslink = self.driver.find_element(By.LINK_TEXT, "Post a Job")
        Jobslink.click()
        AccEmail = self.driver.find_element(By.ID, "create_account_email")
        Jobtitle = self.driver.find_element(By.ID, "job_title")
        Jobloc = self.driver.find_element(By.ID, "job_location")
        AccEmail.send_keys("zxcy123@gmail.com")
        Jobtitle.send_keys("Test_Job")
        Jobloc.send_keys("Mysore")
        self.driver.implicitly_wait(360)
        Jobtype = Select(self.driver.find_element(By.ID,"job_type"))
        Jobtype.select_by_visible_text('Part Time')
        print(Jobtype)
        self.driver.implicitly_wait(360)
        self.driver.execute_script("window.scrollTo(0, window.scrollY + 500)")
        self.driver.switch_to.frame(0)
        elem = self.driver.find_element(By.ID, "tinymce")
        elem.click()
        elem.send_keys("TestJob")
        self.driver.implicitly_wait(500)
        self.driver.switch_to.default_content()
        Application = self.driver.find_element(By.ID, "application")
        cname = self.driver.find_element(By.ID, "company_name")
        cwebsite = self.driver.find_element(By.ID, "company_website")
        ctagline = self.driver.find_element(By.ID, "company_website")
        Application.send_keys("test2@hotmail.com")
        cname.send_keys("IBM")
        cwebsite.send_keys("https://alchemy.hguy.co/jobs/")
        ctagline.send_keys("Test123")
        self.driver.implicitly_wait(500)
        Preview = self.driver.find_element(By.XPATH, "//input[@name='submit_job']")
        Preview.click()
        Pbutton = self.driver.find_element(By.ID, "job_preview_submit_button")
        Pbutton.click()
        self.driver.implicitly_wait(1000)