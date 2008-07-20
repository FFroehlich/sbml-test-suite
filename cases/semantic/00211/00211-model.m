(* 

category:      Test
synopsis:      Basic single forward reaction with two species in a 2-dimensional
compartment, with one species acting as a boundary condition.
componentTags: Compartment, Species, Reaction, Parameter 
testTags:      InitialAmount, 2D-Compartment, BoundaryCondition
testType:      TimeCourse
levels:        2.1, 2.2, 2.3
generatedBy:   Numeric

The model contains one compartment called "compartment".  There are two
species named S1 and S2 and one parameter named k1.  Compartment
"compartment" is two-dimensional.  Species S1 is labeled as an SBML
boundary species.  The model contains one reaction defined as:

[{width:30em,margin-left:5em}|  *Reaction*  |  *Rate*  |
| S1 -> S2 | $k1 * S1 * compartment$  |]

The initial conditions are as follows:

[{width:30em,margin-left:5em}|       |*Value*          |*Units*  |
|Initial amount of S1              |$1.5 \x 10^-15$ |mole                      |
|Initial amount of S2              |$  1 \x 10^-15$ |mole                      |
|Value of parameter k1             |$            1$ |second^-1^ |
|Area of compartment "compartment" |$            1$ |metre^2^                  |]

The species values are given as amounts of substance to make it easier to
use the model in a discrete stochastic simulator, but (as per usual SBML
principles) their symbols represent their values in concentration units
where they appear in expressions.

*)

newcase[ "00211" ];

addCompartment[ compartment, spatialDimensions-> 2, size -> 1 ];
addSpecies[ S1, initialAmount -> 1.5 10^-15, boundaryCondition->True ];
addSpecies[ S2, initialAmount -> 1 10^-15 ];
addParameter[ k1, value -> 1 ];
addReaction[ S1 -> S2, reversible -> False,
	     kineticLaw -> k1 * S1 * compartment ];

makemodel[]