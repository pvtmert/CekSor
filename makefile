
addr=188.166.13.50
port=8111
lvl=5

default: min
	tar -$(args)c * .htaccess $(xf) | xz -zec$(lvl) | cat -u .pwd - | /usr/bin/nc $(addr) $(port)

min:
	rm *.min.js
	@echo "minifying js..."
	@cat *.js | curl -X POST -s --data-urlencode "input=$$(cat -)" "https://javascript-minifier.com/raw" > calc.min.js &
	rm *.min.css
	@echo "minifying css..."
	@for i in *.css; do \
		echo $$i; \
		curl -X POST -s --data-urlencode "input@$$i" 'https://cssminifier.com/raw' > $$(dirname "$$i")/$$(basename "$$i" '.css').min.css & \
		done; \
		while test -n "$$(jobs -r 2>&1)"; do continue; done;
