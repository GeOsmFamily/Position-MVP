FROM python:3.9
COPY ./app /usr/src/app
WORKDIR /usr/src/app
RUN pip3 install -r requirements.txt
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "15500"]