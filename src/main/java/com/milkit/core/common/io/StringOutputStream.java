package com.milkit.core.common.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import com.milkit.core.common.StandardGlobal;


public class StringOutputStream extends OutputStream {


	protected StringBuffer buffer = null;

	public StringOutputStream() {
		buffer = new StringBuffer();
	}

	public void close() throws IOException {
		super.close();
		buffer = null;
	}

	public void flush() throws IOException {
		super.flush();
	}

	@Override
	public void write(byte[] b) throws UnsupportedEncodingException, IOException {
		this.buffer.append(new String(b, StandardGlobal.ENCODING));
	}
	
	@Override
	public void write(byte[] b, int off, int len) throws UnsupportedEncodingException, IOException {
		this.buffer.append(new String(b, off, len, StandardGlobal.ENCODING));
	}

	@Override
	public void write(int b) throws IOException {
		String str = Integer.toString(b);
		this.buffer.append(str);
	}

	public String toString() {
		return buffer.toString();
	}
	
	public StringBuffer getBuffer() {
		return buffer;
	}

}
