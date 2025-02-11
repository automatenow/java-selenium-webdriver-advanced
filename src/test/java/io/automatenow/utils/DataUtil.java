package io.automatenow.utils;

import io.automatenow.core.BaseTest;
import org.testng.annotations.DataProvider;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author Marco A. Cruz
 */
public class DataUtil extends BaseTest {

    @DataProvider
    public static Object[][] dataProvider1() {
        return readYAML("src/test/resources/data2.yaml", "data 1");
    }

    @DataProvider
    public static Object[][] dataProvider2() {
        return readYAML("src/test/resources/data2.yaml", "data 2");
    }

    private static Object[][] readYAML(String filename, String yamlObj) {
        // InputStream to read data
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Create Yaml instance and read yaml file
        Yaml yaml = new Yaml();
        Map<String, Object> data = yaml.load(inputStream);

        // Java array to store YAML data
        Object[][] testData = new Object[1][1];
        testData[0][0] = data.get(yamlObj);

        return testData;
    }

}
