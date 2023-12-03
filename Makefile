.PHONY: docs

docs:
	pandoc docs/clean-architecture.md -o docs/clean-architecture.pdf --pdf-engine=xelatex
