package in.labulle.anycode.xmi.parser.jdom.uml;

import in.labulle.anycode.xmi.parser.IXmiContextParser;

public class PostPonedTask {
	private final String xmiId;

	private final IXmiContextParser<?> parser;

	public PostPonedTask(String xmiId) {
		this.xmiId = xmiId;
		this.parser = null;
	}

	public PostPonedTask(String xmiId, IXmiContextParser<?> parser) {
		super();
		this.xmiId = xmiId;
		this.parser = parser;
	}

	public IXmiContextParser<?> getParser() {
		return parser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((xmiId == null) ? 0 : xmiId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PostPonedTask other = (PostPonedTask) obj;
		if (xmiId == null) {
			if (other.xmiId != null)
				return false;
		} else if (!xmiId.equals(other.xmiId))
			return false;
		return true;
	}

}
