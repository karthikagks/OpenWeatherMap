#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Register Weather station
  I want to use this template for my feature file

  @tag1
  Scenario: Register weather station without an API Key
    Given Attempt to register a station without a API key
    Then Verify that attempt to register a station without API key is failed.
    
  @tag2
  Scenario: Register weather station with an API Key
    Given Attempt to register a First station with an API key
    Then Verify that http response code for an attempt to register a station with API key is success.
    Given Attempt to register a Second station with an API key
    Then Verify that http response code for an attempt to register a station with API key is success.
  @tag3
  Scenario:  Verify station details as per registration request in DB.
		Given List the stations details stored in DB
		Then Verify that station details are stored successfully in DB as specified in the registration request.
  
