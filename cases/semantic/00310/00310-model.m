(* 

category:      Test
synopsis:      Basic single forward reaction with two species in a compartment
               whose volume is varying.
componentTags: Compartment, Species, Reaction, Parameter, AssignmentRule 
testTags:      InitialAmount, NonConstantCompartment
testType:      TimeCourse
levels:        1.2, 2.1, 2.2, 2.3
generatedBy:   Numeric

The model contains one varying compartment called "compartment".  There are two
species called S1 and S2 and two parameters called k1 and p1.  The model
contains one reaction defined as:

[{width:30em,margin-left:5em}|  *Reaction*  |  *Rate*  |
| S1 -> S2 | $k1 * S1 * compartment$  |]

The model contains one rule which assigns value to compartment:

[{width:30em,margin-left:5em}|  *Type*  |  *Variable*  |  *Formula*  |
 | Assignment | compartment | $p1 * S1$  |]

In this case the initial value declared for "compartment" is consistent
with that calculated by the assignmentRule.  Note that since this
assignmentRule must always remain true, it should be considered during
simulation.

The initial conditions are as follows:

[{width:30em,margin-left:5em}|       |*Value*          |*Units*  |
|Initial amount of S1                |$          1.5$ |mole                      |
|Initial amount of S2                |$            0$ |mole                      |
|Value of parameter k1               |$          0.9$ |second^-1^ |
|Value of parameter p1               |$          0.1$ |litre^2^ mole^-1^ |
|Volume of compartment "compartment" |$         0.15$ |litre                     |]

The species values are given as amounts of substance to make it easier to
use the model in a discrete stochastic simulator, but (as per usual SBML
principles) their symbols represent their values in concentration units
where they appear in expressions.

*)

newcase[ "00310" ];

addCompartment[ compartment, size->0.15, constant->False ];
addSpecies[ S1, initialAmount -> 1.5 ];
addSpecies[ S2, initialAmount -> 0 ];
addParameter[ k1, value -> 0.9 ];
addParameter[ p1, value -> 0.1 ];
addRule[ type->AssignmentRule, variable -> compartment, math -> p1 * S1];
addReaction[ S1 -> S2, reversible -> False,
	     kineticLaw -> k1 * S1 * compartment ];

makemodel[]