Feature: Get Weather Details

  @WeatherSearch
  Scenario Outline: Validate the weather details for the rqeuired cities

    Given user searches weather details of "<City>" with <key>
    When the weather details are available
    Then verify details are for <City>
    Then verify <Field> are available

    Examples: 
      | City      | Field                           |key|
      | Amsterdam | current.weather_descriptions[0] |79b1c759f82c6fdbd3553ce5eda122af|
      | London    | current.weather_descriptions[0] |79b1c759f82c6fdbd3553ce5eda122af|
