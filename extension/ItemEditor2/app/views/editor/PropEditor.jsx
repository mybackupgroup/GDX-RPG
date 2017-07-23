import React from 'react';
import Editor from "./Editor";

export default class PropEditor extends Editor {
	constructor(props){
		super(props)
	}

	render(){
		return super.renderContainer(
			<div>
				<pre>
					{this.state.text}
				</pre>
			</div>
		)
	}
}