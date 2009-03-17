(* 

category:      Test
synopsis:      Model using rules and parameters with a functionDefinition.
componentTags: Parameter, FunctionDefinition, RateRule 
testTags:      NonConstantParameter
testType:      TimeCourse
levels:        2.1, 2.2, 2.3, 2.4
generatedBy:   Numeric

The model contains two varying parameters called S1 and S2 and one constant
parameter called k1.  (Note that indeed S1 and S2 are parameters and not 
species in this model.)

The model contains two rules:

[{width:30em,margin-left:5em}|  *Type*  |  *Variable*  |  *Formula*  |
 | Rate | S1 | $-multiply(k1, S1)$  |
 | Rate | S2 | $multiply(k1, S1)$  |]


The model contains one functionDefinition defined as:

[{width:30em,margin-left:5em}|  *Id*  |  *Arguments*  |  *Formula*  |
 | multiply | x, y | $x * y$ |]


The initial conditions are as follows:

[{width:30em,margin-left:5em}|      |*Value*          |*Units*  |
|Value of parameter S1 |$1.5 \x 10^-2$ |any |
|Value of parameter S2 |$1.5 \x 10^-2$ |same as S1 |
|Value of parameter k1 |$            1$ |second^-1^ |]


*)

newcase[ "00171" ];

addFunction[ multiply, arguments -> {x, y}, math -> x * y];
addParameter[ S1, value -> 1.5 10^-2, constant -> False ];
addParameter[ S2, value -> 1.5 10^-2, constant -> False ];
addParameter[ k1, value -> 1 ];
addRule[ type->RateRule, variable -> S1, math -> -multiply[k1,S1]];
addRule[ type->RateRule, variable -> S2, math -> multiply[k1,S1]];

makemodel[]
