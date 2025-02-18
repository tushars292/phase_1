package org.lockers.code;

import java.io.File;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Filedelete {
	private String wrngoptsel;
	private String projectendtag;
	Scanner sc;
	private String dirfilepath;
	Filesearch fs = new Filesearch();

	public Filedelete(String wrngoptsel, String projectendtag, String dirfilepath, Scanner sc) {
		super();
		this.wrngoptsel = wrngoptsel;
		this.projectendtag = projectendtag;
		this.sc = sc;
		this.dirfilepath = dirfilepath;
	}

	public Filedelete() {

	}

	void file_delete() throws BussinessException, BussinessmainmenuException {
		File dirfile = new File(dirfilepath);
		File[] arrfiles = dirfile.listFiles();
		int k = 0;
		if (arrfiles.length > 0) {
			Boolean optionflag = true;
			while (optionflag) {
				System.out.println(" Please enter the name of file you want to delete ");

				String filename = sc.nextLine();
				String filepath = dirfilepath + "/" + filename + ".txt";

				try {
					File fn = new File(filepath);
					if (fn.isFile()) {
						Boolean fcheck = false;
						for (int i = 0; i < arrfiles.length; i++) {

							if (fn.getName().equals(arrfiles[i].getName())) {
								fcheck = true;
								break;
							}

						}

						if (fcheck) {
							fn.delete();
							System.out.printf(" File %s was deleted succesfully", filename);
							break;
						} else {

							k++;
							if (k > 1) {
								System.out.println("You have reached maxmium limit ");
								break;
							}
							System.out.printf(" File %s cannot be found please choose one of the following file",
									filename);

					fs.printfilename(arrfiles);
						}
					} else {
						k++;
						if (k > 1) {
							System.out.println("You have reached maxium limit ");
							break;
						}
						System.out.printf(" File %s cannot be found please choose one of the following file", filename);

						fs.printfilename(arrfiles);
					}

				} catch (NoSuchElementException e) {
					k++;
					if (k > 1) {
						System.out.println("You have reached maximum limit ");
						break;
					}
					System.out.printf(" File %s cannot be found please choose one of the following file", filename);
					fs.printfilename(arrfiles);
				}

			}

		} else {

			throw new BussinessException("There are no files in the Folder : " + dirfile.getName()
					+ ", Please create file before selecting this option ");
		}

		Gobacktoppreviousmennu gbpm = new Gobacktoppreviousmennu(projectendtag, wrngoptsel, sc);
		gbpm.goback();

	}

}
