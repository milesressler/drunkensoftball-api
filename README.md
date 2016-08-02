# drunkensoftball

Host: drunkensoftball.miles-smiles.us

# Create User
Creates a new user and returns the user's auth token

POST /user
authenticationType = String, one of: google, facebook, password

Body:
{
    "username":"milestest",
    "authenticationType":"password",
    "email": "asdf@s.com"
}

Returns:
Access token for other requests - not currently recoverable


# Create Team
Creates a new team with the provided name.  The user on auth token will be set as team manager and automatically added to the roster.

POST /team
Header: Authorization={UUID from create user}

Body:
{
    "name":"Make it a Double"
}

Returns:
UUID of team


# Add Player to Team
Adds a new players to the team.  New user will be created using the provided display name, but no token will be generated, so use can't login, but is usable by the manager

PUT /team
Header: Authorization={UUID from create user}

{
    "displayName":"Tony the Tiger",
    "teamUuid": "4f6f8680-38a1-46c1-9028-51b6f149f0ef"
}

Returns HTTP 200, empty body


# Get Team by UUID
Get the name, manager, and players list for a team managed by the authorized user making request

GET /team/{UUID}
Header: Authorization={UUID from create user}

Sample Response:
{
  "uuid": "bb5f0b03-c87f-4940-ac97-41d50c5422a0",
  "created": 1469936133000,
  "name": "Make it a Double",
  "manager": {
    "uuid": "84975e19-9c46-40ba-b844-8a6c8ad63215",
    "created": 1469936123000,
    "displayName": "milestest",
    "username": "milestest"
  },
  "players": [
    {
      "uuid": "84975e19-9c46-40ba-b844-8a6c8ad63215",
      "created": 1469936123000,
      "displayName": "milestest",
      "username": "milestest"
    }
  ]
}


# Get Owned Teams List
Returns list of teams owned. Is pageable, default to 10 per page i think

GET /team
Header: Authorization={UUID from create user}

Sample Response:
[
  {
    "uuid": "bb5f0b03-c87f-4940-ac97-41d50c5422a0",
    "created": 1469936133000,
    "name": "Make it a Double",
    "manager": {
      "uuid": "84975e19-9c46-40ba-b844-8a6c8ad63215",
      "created": 1469936123000,
      "displayName": "milestest",
      "username": "milestest"
    },
    "players": [
      {
        "uuid": "84975e19-9c46-40ba-b844-8a6c8ad63215",
        "created": 1469936123000,
        "displayName": "milestest",
        "username": "milestest"
      }
    ]
  },
  {
    "uuid": "4f6f8680-38a1-46c1-9028-51b6f149f0ef",
    "created": 1469936379000,
    "name": "Make it a Double2",
    "manager": {
      "uuid": "84975e19-9c46-40ba-b844-8a6c8ad63215",
      "created": 1469936123000,
      "displayName": "milestest",
      "username": "milestest"
    },
    "players": [
      {
        "uuid": "84975e19-9c46-40ba-b844-8a6c8ad63215",
        "created": 1469936123000,
        "displayName": "milestest",
        "username": "milestest"
      },
      {
        "uuid": "afa3ed2e-773c-43a8-8121-f80eabe337c0",
        "created": 1469936427000,
        "displayName": "Tony the Tiger",
        "username": "c4595f67ace64ecf856af27a4498e402"
      }
    ]
  }
]