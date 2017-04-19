.PHONY: \
	clean \
	run \
	dist \
	toc \

clean:
	sbt clean

run:
	sbt run

dist:
	sbt 'show dist'

DOCUMENTS = \
	documentation/analysis.md \
	documentation/glossary.md \
	documentation/solution.md \
	documentation/specification.md \
	documentation/development.md \

toc:
	which markdown-toc || npm install -g markdown-toc
	$(foreach DOCUMENT,$(DOCUMENTS),markdown-toc -i $(DOCUMENT);)

nav:
	$(foreach DOCUMENT,$(DOCUMENTS),node documentation/templates/apply-template.js $(DOCUMENT);)