import React, {useState} from 'react';

function App() {

    const [data, setData] = useState();
    const [isLoading, setIsLoading] = useState(false);
    const [err, setErr] = useState('');

    const handleClick = async () => {
        setIsLoading(true);

        fetch('http://localhost:8080/create_room', {
                        // mode: 'no-cors',
                        method: 'POST',
                        body: JSON.stringify({
                            name: 'John Smith',
                            job: 'manager',
                        }),
                        headers: {
                            'Content-Type': 'application/json',
                            Accept: 'application/json',
                        },
                    })
            .then(response => {
                if (!response.ok) {
                    console.log(response);
                    throw new Error(response.statusText);
                }


                // console.log("result" + response);
                return  response.json();

            }).then(
                result => {

                    console.log('result is: ', JSON.stringify(result, null, 4));

                    console.log("result: " + result);
                    // Here is where you put what you want to do with the response.
                }
        )
            .catch(error => {
                console.log('Looks like there was a problem: \n', error);
            });
    };

    console.log(data);


  return (
    <div className="App">
      Прилоежение работает 3
      <button onClick={handleClick}>Создать новую комнату</button>
    </div>
  );
}

export default App;
