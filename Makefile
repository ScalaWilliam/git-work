.PHONY: \
	clean \
	run \
	dist \

clean:
	sbt clean

run:
	sbt run

dist:
	sbt 'show dist'
