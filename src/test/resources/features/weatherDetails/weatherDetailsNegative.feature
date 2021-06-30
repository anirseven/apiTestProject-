Feature: Proper Message for incorrect key

  @WeatherSearch
  Scenario Outline: Validate the weather if key or city details are incorrect proper message is received
    Given user searches weather details of "<City>" with <key>
    When the weather details are available
    Then verify if proper <errorMessage> is received

    Examples: 
      | City      | Field                           | key  | errorMessage                                                                            |
      | Amsterdam | current.weather_descriptions[0] | 7788 | You have not supplied a valid API Access Key. [Technical Support: support@apilayer.com] |
