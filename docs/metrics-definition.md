# Code Metrics Definition

This project uses standard software engineering metrics as input features
for the machine learning model.

## Metrics Used

### 1. Lines of Code (LOC)
Total number of lines in a Java source file.
Higher LOC often increases maintenance difficulty.

### 2. Method Count
Number of methods defined in a class.
Too many methods may indicate poor class responsibility separation.

### 3. Cyclomatic Complexity (Approximation)
Estimated using LOC per method.
Higher complexity increases bug probability.

### 4. Comment Ratio
Ratio of comment lines to total lines.
Low comment ratio reduces code readability.

### 5. Coupling
Estimated using number of import statements.
High coupling reduces modularity.

These metrics are extracted automatically and passed as features
to the machine learning classifier.
