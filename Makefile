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

toc:
	markdown-toc -i README.md || npm install -g markdown-toc && markdown-toc -i README.md

