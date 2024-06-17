import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By


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