@API @CityBike @SmokeTestSuite @E2ETestSuite
Feature: CityBike API tests
"""
  As a biker I would like to know the exact location of city bikes around the world in a given application.
  """

  Scenario: Validate the networks Info
    Given developer request cityBik API Url
    Then Verify if the status code is 200
    And Verify if the response contains network info

  Scenario Outline: Validate city and corresponding lat long
    Given developers pass network id "<networkId>"
    Then network location corresponded "<city>" in "<country>"
    And corresponded location "<latitude>", "<longitude>" returned
    Examples:
      | networkId      | city      | country | latitude | longitude |
      | visa-frankfurt | Frankfurt | DE      | 50.1072  | 8.66375   |

  Scenario Outline: Validate free bikes at stations
    Given developers pass network id "<networkId>"
    Then number of stations should be returned
    And validate available bikes, timestamps
    Examples:
      | networkId       |
      | visa-frankfurt  |
      | velobike-moscow |
      | nu-connect      |

  Scenario: Validate Field filtering response
    Given developer request field filtering  with "id,name,href"
    Then Verify if the status code is 200
    Then developer gets render response with just the name, id, href
