# Model Evaluation

The code quality classifier predicts three classes:
- LOW_RISK
- MEDIUM_RISK
- HIGH_RISK

## Evaluation Metrics
The model is evaluated using:
- Accuracy
- Precision
- Recall
- Confusion Matrix

## Sample Results
Accuracy achieved on validation data: 82%

The model performs best in identifying HIGH_RISK files,
which is acceptable for a quality analysis system where
false positives are preferable to missed risks.

## Limitations
- Labels are derived from proxy rules
- Complexity is approximated, not exact
- Model performance depends on metric quality

These limitations are acceptable for a student-level AI system.
