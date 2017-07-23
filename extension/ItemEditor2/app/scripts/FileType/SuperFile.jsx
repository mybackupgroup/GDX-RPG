import File from "../File";


export default class SuperFile{
	label = "";
	fileName = "";
	path = "";
	errorFormat = false;
	fileText = "";
	prefix = null;

	deleted = false;

	modified = false;

	static list(callback) {
		if(callback)
			return File.list(this.path(), callback);
		return new Promise(async (resolve, reject) => resolve(await File.list(this.path())));
	}

	static read(file, name, callback){
		let absPath = this.path() + name;
		File.read(absPath, e => {

			file.fileName = name;
			file.path = absPath;
			file.fileText = e;

			file.parse();

			callback(file);
		});
	}

	save(callback) {
		File.write(this.path, this.fileText.toString(), () => {
			this.deleted = false;

			if(callback)
				callback();
		});
	}

	delete(callback) {
		File.delete(this.path, () => {
			this.deleted = true;

			if(callback)
				callback();
		});
	}

	object() {
		try{
			return eval("(" + this.fileText + ")")
		}catch(e){
			return null;
		}
	}

	static equals(file1, file2){
		return file1.fileName == file2.fileName && file1.$static.type == file2.$static.type;
	}

	static create(id, path, file){
		file.label = "新建文件";
		file.fileName = id + ".grd";
		file.path = path + file.fileName;

		return file;
	}
}