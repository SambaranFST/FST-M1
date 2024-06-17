from selenium.webdriver.support import expected_conditions as EC
import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.select import Select
from selenium.webdriver.support.wait import WebDriverWait



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

    def test_navigation(self):
        self.driver.get("https://alchemy.hguy.co/jobs/wp-admin")

        username = self.driver.find_element(By.ID, "user_login")
        password = self.driver.find_element(By.ID, "user_pass")

        username.send_keys("root")
        password.send_keys("pa$$w0rd")

        login = self.driver.find_element(By.ID, "wp-submit")
        login.click()
        JobTitle = self.driver.title
        Expected_title = "Dashboard ‹ Alchemy Jobs — WordPress"

        assert JobTitle == Expected_title, "Title mismatch"

        Joblist = self.driver.find_element(By.ID, "menu-posts-job_listing")
        Joblist.click()
        #Addnew = self.driver.find_element(By.CSS_SELECTOR, "#wpbody-content > div.wrap > a")
        New = self.driver.find_element(By.CSS_SELECTOR, "#menu-posts-job_listing > ul > li:nth-child(3) > a")
        New.click()
        self.driver.implicitly_wait(360)
        h1 = self.driver.find_element(By.CLASS_NAME, "edit-post-welcome-guide__heading")
        print("h1")
        button1 = self.driver.find_element(By.CSS_SELECTOR, ".components-guide__forward-button")
        button1.click()
        self.driver.implicitly_wait(20)
        button2 = self.driver.find_element(By.CSS_SELECTOR, ".components-guide__forward-button")
        button2.click()
        self.driver.implicitly_wait(20)
        button3 = self.driver.find_element(By.CSS_SELECTOR, ".components-guide__forward-button")
        button3.click()
        self.driver.implicitly_wait(20)
        finishbutton = self.driver.find_element(By.CSS_SELECTOR, ".components-guide__finish-button")
        finishbutton.click()
        self.driver.implicitly_wait(300)
        Post = self.driver.find_element(By.ID, "post-title-0")
        Post.send_keys("test1")
        self.driver.implicitly_wait(360)
        Publish = self.driver.find_element(By.XPATH, "//button[contains(.,'Publish…')]")
        Publish.click()
        self.driver.implicitly_wait(360)
        Publish1 = self.driver.find_element(By.CSS_SELECTOR, ".editor-post-publish-button")
        Publish1.click()
        ViewJob = self.driver.find_element(By.XPATH,"//*[@id='editor']/div/div/div[1]/div/div[2]/div[3]/div/div/div/div[2]/div/div[2]/div[2]/a")
        ViewJob.click()