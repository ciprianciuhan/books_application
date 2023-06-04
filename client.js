//this will handle http errors
function handleErrors(response) {
  if (!response.ok) {
    throw Error(response.statusText);
  }
  return response;
}

const baseURL = 'http://localhost:8080';

async function createAuthor(authorData) {
  try {
    const response = await fetch(`${baseURL}/authors/insertAuthor`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(authorData),
    });
    handleErrors(response);

    const createdAuthor = await response.json();

    console.log('New Author:', createdAuthor);
    return createdAuthor.id; // Return the created author's ID
  } catch (error) {
    console.error('Error creating author:', error);
  }
}

async function createBook(bookData) {
  try {
    const response = await fetch(`${baseURL}/books/insertBook`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(bookData),
    });
    handleErrors(response);

    const createdBook = await response.json();

    console.log('New Book:', createdBook);
  } catch (error) {
    console.error('Error creating book:', error);
  }
}

async function getBookById(bookId) {
  try {
    const response = await fetch(`${baseURL}/books/getBookById?id=${bookId}`);
    handleErrors(response);

    const book = await response.json();

    console.log('Book:', book);
  } catch (error) {
    console.error('Error retrieving book by ID:', error);
  }
}

async function updateBook(bookId, updatedBookData) {
  try {
    const response = await fetch(`${baseURL}/books/updateBook?id=${bookId}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(updatedBookData),
    });
    handleErrors(response);

    const updatedBook = await response.json();

    console.log('Updated Book:', updatedBook);
  } catch (error) {
    console.error('Error updating book:', error);
  }
}

async function deleteBookById(bookId) {
  try {
    const response = await fetch(`${baseURL}/books/deleteBook?id=${bookId}`, {
      method: 'DELETE',
    });
    handleErrors(response);

    console.log('Book deleted successfully');
  } catch (error) {
    console.error('Error deleting book:', error);
  }
}

async function getAllBooks() {
  try {
    const response = await fetch(`${baseURL}/books/getBooks`);
    handleErrors(response);

    const books = await response.json();

    console.log('All Books:', books);
  } catch (error) {
    console.error('Error retrieving all books:', error);
  }
}

async function getBooksPerPage(offset, pageSize) {
  try {
    const response = await fetch(`${baseURL}/books/getBooksPagination/${offset}/${pageSize}`);
    handleErrors(response);

    const books = await response.json();

    console.log(`Books (Offset: ${offset}, PageSize: ${pageSize}):`, books);
  } catch (error) {
    console.error('Error retrieving books per-page:', error);
  }
}

async function getAllBooksWithAuthors() {
  try {
    const response = await fetch(`${baseURL}/books/getAllBooksWithAuthorInfo`);
    handleErrors(response);

    const booksWithAuthors = await response.json();

    console.log('Books with Authors:', booksWithAuthors);
  } catch (error) {
    console.error('Error retrieving books with authors:', error);
  }
}

async function getBooksWithAuthorsPerPage(offset, pageSize) {
  try {
    const response = await fetch(`${baseURL}/books/getAllBooksWithAuthorInfoPagination/${offset}/${pageSize}`);
    handleErrors(response);

    const booksWithAuthors = await response.json();

    console.log(`Books with Authors (Offset: ${offset}, PageSize: ${pageSize}):`, booksWithAuthors);
  } catch (error) {
    console.error('Error retrieving books with authors per-page:', error);
  }
}


//create a new author and book
const newAuthorData = {
  name: 'John Doe',
  email: 'johndoe@example.com',
};

const newBookData = {
  title: 'The Book Title',
  description: 'Lorem ipsum dolor sit amet',
  authorId: 1,
};

createAuthor(newAuthorData)

createBook(newBookData)

//retrieve a book by id
const bookId = 1;
getBookById(bookId)

//update an existing book
const updatedBookData = {
  title: 'Updated Title',
  description: 'Updated description',
};

updateBook(bookId, updatedBookData);

//delete a book by id
deleteBookById(bookId);

//retrieve all books
getAllBooks();

//retrieve n books per-request (apply pagination)
const offset = 0;
const pageSize = 10;
getBooksPerPage(offset, pageSize);

//retrieve all books with their corresponding authors
getAllBooksWithAuthors();

//retrieve n books with their corresponding authors per-request (apply pagination)
getBooksWithAuthorsPerPage(offset, pageSize);
