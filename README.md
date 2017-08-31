# Ticket-system

### Setup
1. Start server
```
gradlew clean bootRun
```
2. REST API provide following endpoints for manipulation with tickets:
```
PUT http://localhost:8080/rest/ticket
GET http://localhost:8080/rest/ticket/current
DELETE http://localhost:8080/rest/ticket/current
```

### Description

Napište program, který bude vystavovat REST rozhraní pro ticketovací systém na pobočce. Ten by měl vystavovat následující REST služby:
- Vygenerování pořadového čísla, s tím že vracet bude navíc ještě datum a čas vygenerování a pořadí ve frontě
- Získání aktuálního aktivního čekajícího čísla
- Smazání posledního aktivního čísla
 
 
Například:
V seznamu aktivních ticketů bude (vždy v pořadí id, timestamp, pořadí):
- 1245, 2017-09-01 15:22, 0
- 1246, 2017-09-01 15:42, 1
- 1250, 2017-09-01 16:32, 2
 
Vygenerování nového vrátí:
- 1251, 2017-09-01 19:20, 3
 
Získání aktuálního vrátí:
- 1245, 2017-09-01 15:22, 0
 
Po smazání posledního bude v seznamu:
- 1246, 2017-09-01 15:42, 0
- 1250, 2017-09-01 16:32, 1
- 1251, 2017-09-01 19:20, 2
 
Úloha by měla být na cca 1-2 hodin.
