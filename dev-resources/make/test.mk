kibit:
	@lein with-profile +test kibit

bikeshed:
	@lein with-profile +test bikeshed

base-eastwood:
	@lein with-profile +test eastwood "$(EW_OPTS)"

yagni:
	@lein with-profile +test yagni

eastwood:
	@EW_OPTS="{:namespaces [:source-paths]}" make base-eastwood

lint: kibit eastwood bikeshed yagni

lint-unused:
	@EW_OPTS="{:linters [:unused-fn-args :unused-locals :unused-namespaces :unused-private-vars :wrong-ns-form] :namespaces [:source-paths]}" make base-eastwood

lint-ns:
	@EW_OPTS="{:linters [:unused-namespaces :wrong-ns-form] :namespaces [:source-paths]}" make base-eastwood

check: lint check-deps
	@lein test

check-deps:
	@lein with-profile +test ancient check.check-profiles
