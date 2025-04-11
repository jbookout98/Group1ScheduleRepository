import PyPDF2
import csv
import re

def extract_text_from_pdf(file_path):
    text = ""
    try:
        with open(file_path, 'rb') as file:
            reader = PyPDF2.PdfReader(file)
            for page in reader.pages:
                text += page.extract_text() + "\n"
    except Exception as e:
        print(f"Error reading PDF file: {e}")
    return text

def parse_courses(schedule_text):
    lines = schedule_text.split('\n')
    courses = []
    in_schedule_section = False
    current_line = ""

    for line in lines:
        line = line.strip()
        if not line:
            continue

        # Check if we are at the beginning of the schedule section
        if "Spring 2024" in line or "Schedule of Classes" in line:
            in_schedule_section = True
            continue

        if not in_schedule_section:
            continue

        # Skip header lines
        if "Crn" in line and "Subject" in line and "Number" in line:
            continue

        # Identify the start of a new course line by detecting the CRN pattern (5 digits)
        if re.match(r'\d{5}', line):
            # If we have accumulated text from a previous line, parse it
            if current_line:
                course = parse_course_from_line(current_line)
                if course:
                    courses.append(course)
            # Start a new line
            current_line = line
        else:
            # Append the continuation of the current line
            current_line += " " + line

    # Parse the last accumulated line
    if current_line:
        course = parse_course_from_line(current_line)
        if course:
            courses.append(course)

    return courses

def parse_course_from_line(line):
    # Extract the CRN, which is always the first 5 digits
    print(line +"\n")
    crn = line[:5].strip()
    line = line[5:].strip()

    # Extract the subject, which can be either 2-4 characters or 'A S' pattern
    subject_match = re.match(r'\s*([A-Z]{2,4}|A\sS|B\sA)(\s|$)', line)
    if subject_match:
        subject = subject_match.group(1).strip()
        line = line[len(subject_match.group(0)):].strip()
    else:
        subject = ""

    # Extract the number, which can be up to 5 characters (e.g., 4371U)
    number_match = re.match(r'\s*([A-Z0-9]{4,5})(\s|$)', line)
    if number_match:
        number = number_match.group(1).strip()
        line = line[len(number_match.group(0)):].strip()
    else:
        number = ""

    # Extract the section (next 3 characters the section, which should be 2-3 alphanumeric characters (e.g., '89', '89C', 'L01')
    # Extract the section, which is mostly 2-3 digits but can sometimes be alphanumeric (e.g., '89', '123', 'L01')
        # Trim any leading spaces first
    # Trim any leading spaces first
    line = line.strip()

    # Check if the section starts with 'L', 'D', 'H', or a digit
    if line and (line[0] == 'L' or line[0] == 'D' or line[0] == 'H'):
        # If the section starts with 'L', 'D', or 'H', select the character and the next two characters (total three characters)
        if len(line) >= 3:
            section = line[:3]
            line = line[3:].strip()
        else:
            section = line  # In case there are fewer characters left
            line = ""
    elif line and line[0].isdigit():
        # If it starts with a digit, extract 2-3 digits
        section_match = re.match(r'\d{2,3}', line)
        if section_match:
            section = section_match.group(0)
            line = line[len(section):].strip()
        else:
            section = ""
    else:
        # If it does not start with 'L', 'D', 'H', or a digit, we cannot extract a valid section
        section = ""





    dept_match = re.match(r'\s*([A-Z]{2,4}|A\sS)(\s|$)', line)
    if dept_match:
        department = dept_match.group(1).strip()
        line = line[len(dept_match.group(0)):].strip()
    else:
        department = ""

    # Extract the campus location, which can be 'M', 'O', or 'RRC'
    campus_match = re.match(r'\s*(M|O|RRC)(\s|$)', line)
    if campus_match:
        campus_loc = campus_match.group(1).strip()
        line = line[len(campus_match.group(0)):].strip()
        if campus_loc == "M":
            campus_loc = "601 University Dr, San Marcos, TX 78666"
        elif campus_loc == "RRC":
            campus_loc = "1555 University Blvd, Round Rock, TX 78665"
        else:
            campus_loc = "Online"
    else:
        campus_loc = "Online"

    # Extract the type of class, which is 3 characters (e.g., 'LEC', 'LAB')
    class_type = line[:3].strip()
    line = line[3:].strip()

    # Extract the method, which is the next 3 characters after the class type
    method = line[:3].strip()
    line = line[3:].strip()

    # Extract DP or IN, if present (or leave blank if not present)
    dp_or_in_match = re.match(r'\s*(DP|IN)?(\s|$)', line)
    if dp_or_in_match:
        dp_or_in = dp_or_in_match.group(1).strip() if dp_or_in_match.group(1) else ""
        line = line[len(dp_or_in_match.group(0)):].strip()
    else:
        dp_or_in = ""

    # Extract PoT, which is an integer
    pot_match = re.match(r'\s*(\d+)(\s|$)', line)
    if pot_match:
        pot = pot_match.group(1).strip()
        line = line[len(pot_match.group(0)):].strip()
    else:
        pot = ""

    # Extract Actual, which is an integer with 1-3 digits
    actual_match = re.match(r'\s*(\d{1,3})(\s|$)', line)
    if actual_match:
        actual = actual_match.group(1).strip()
        line = line[len(actual_match.group(0)):].strip()
    else:
        actual = ""

    # Extract Max, which is an integer with 1-3 digits, following directly after Actual
    line = line.lstrip()  # Strip any leading spaces before extracting Max
    max_match = re.match(r'(\d{1,3})', line)
    if max_match:
        max_value = max_match.group(1).strip()
        line = line[len(max_match.group(0)):].strip()
    else:
        max_value = ""

    # Extract Days, which is 7 characters long
    days = line[:7].strip()
    line = line[7:].strip()

    # Extract Start Time, which is in the format '00:00 AM/PM'
    start_time_match = re.match(r'\s*(\d{1,2}:\d{2}\s[APM]{2})(\s|$)', line)
    if start_time_match:
        start_time = start_time_match.group(1).strip()
        line = line[len(start_time_match.group(0)):].strip()
    else:
        start_time = ""

    # Extract End Time, which is in the format '00:00 AM/PM'
    end_time_match = re.match(r'\s*(\d{1,2}:\d{2}\s[APM]{2})(\s|$)', line)
    if end_time_match:
        end_time = end_time_match.group(1).strip()
        line = line[len(end_time_match.group(0)):].strip()
    else:
        end_time = ""

    # Extract Building, which is 2-4 characters long and should be text only
    # Extract Building, which is 2-4 characters long and should be text only
    building_match = re.match(r'^\s*([A-Z]{2})(?=P|\s|$)|\s*([A-Z]{2,4})(\s|$)', line)
    if building_match:
        building = building_match.group(1) if building_match.group(1) else building_match.group(2)
        building = building.strip()
        line = line[len(building_match.group(0)):].strip()

    else:
        building = ""
        
        

    # Trim any leading spaces before attempting to match
    line = line.strip()

    # Extract Room, if it starts with 'A', select 'A' followed by two characters, or if it starts with a digit, select three digits
 
    room_match = re.match(r'\s*(P\w{5}|A\w{2}|[A-Z]-[\w\.]{4}|[A-Z]{2}\d{1}|\d-\d{2}\.\d|\d{3}|[A-Z]{3})', line)
    if room_match:
        room = room_match.group(1).strip()
        line = line[len(room_match.group(0)):].strip()
    else:
        room = ""



    # Extract Instructor Name, ensuring it's not confused with Building or Room data
    instructor_match = re.search(r'([A-Za-z]+,\s[A-Za-z]+(\s[A-Za-z]+)?)', line)
    if instructor_match:
        instructor = instructor_match.group(0).strip()
        instructor = re.sub(r'\s*,\s*', ', ', instructor).title()
    else:
        instructor = ""
    return [crn, subject, department, number, section, instructor.strip(), days, start_time, end_time, building, room, campus_loc, class_type, method, dp_or_in, pot, actual, max_value]

def write_courses_to_csv(courses, output_file):
    headers = ["CRN", "Subject", "Department", "Number", "Section", "Instructor", "Days", "Start Time", "End Time", "Building", "Room", "Campus", "Class Type", "Method", "DP or IN", "PoT", "Actual", "Max"]

    try:
        with open(output_file, mode='w', newline='') as file:
            writer = csv.writer(file)
            writer.writerow(headers)
            writer.writerows(courses)
    except IOError as e:
        print(f"Error writing to CSV file: {e}")

# Example usage
if __name__ == "__main__":
    file_path = "Spring2024.pdf"
    output_csv = "courses.csv"
    schedule_text = extract_text_from_pdf(file_path)
    courses = parse_courses(schedule_text)
    write_courses_to_csv(courses, output_csv)
