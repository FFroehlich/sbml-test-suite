(* 

category:      Test
synopsis:      Two reactions with three species in one compartment, 
with an algebraic rule used to determine value of a parameter.
componentTags: Compartment, Species, Reaction, Parameter, AlgebraicRule 
testTags:      InitialAmount
testType:      TimeCourse
levels:        1.2, 2.1, 2.2, 2.3, 2.4
generatedBy:   Numeric

The model contains one compartment called C.  There are three
species called X0, X1 and T and two parameters called k1 and k2.  The model
contains two reactions defined as:

[{width:30em,margin-left:5em}|  *Reaction*  |  *Rate*  |
| X0 -> T     | $C * k1 * X0$  |
| T -> X1     | $C * k2 * T$  |]

The model contains one rule which must be used to determine the value of
parameter k2:

[{width:30em,margin-left:5em}|  *Type*  |  *Variable*  |  *Formula*  |
 | Algebraic |   n/a    | $k2 - 0.2$  |]

The initial conditions are as follows:

[{width:30em,margin-left:5em}| |*Value* |*Units*  |
|Initial amount of X0          |$1.25$  |mole                      |
|Initial amount of X1          |$1.5$   |mole                      |
|Initial amount of T           |$1$     |mole                      |
|Value of parameter k1         |$0.1$   |second^-1^            |
|Value of parameter k2         |$undeclared$   |second^-1^                |
|Volume of compartment C       |$1$     |litre                     |]

The species values are given as amounts of substance to make it easier to
use the model in a discrete stochastic simulator, but (as per usual SBML
principles) their symbols represent their values in concentration units
where they appear in expressions.

*)

newcase[ "00537" ];

addCompartment[ C, size -> 1 ];
addSpecies[ X0, initialAmount -> 1.25];
addSpecies[ X1, initialAmount -> 1.5];
addSpecies[ T, initialAmount -> 1];
addParameter[ k1, value -> 0.1 ];
addParameter[ k2, constant -> False ];
addRule[ type->AlgebraicRule, math -> k2 - 0.2];
addReaction[ X0 -> T, reversible -> False,
	     kineticLaw -> C * k1 * X0 ];
addReaction[ T -> X1, reversible -> False,
	     kineticLaw -> C * k2 * T ];

makemodel[]
